package com.virtualfittingroom.data.restApi;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.virtualfittingroom.data.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class AuthApi {
    private static final String TAG = "AuthApi";

//    Shared preferences
    public static final String KEY_AUTH = "AUTH";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    public SharedPreferences sharedPreferences;
    Retrofit retrofit;
    String authToken, androidId;
    AuthApiInterface authApiInterface;

    public AuthApi(Context context){
        this.retrofit = new RetrofitBuilder("", context).build();
        this.authApiInterface = this.retrofit.create(AuthApiInterface.class);
        this.sharedPreferences = context.getSharedPreferences(KEY_AUTH, Context.MODE_PRIVATE);
        this.androidId =
                Settings.Secure.getString(context
                .getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

//    build authApi by token
    public AuthApi(String authToken, Context context){
        this.authToken = authToken;
        this.retrofit = new RetrofitBuilder(this.authToken, context).build();
        this.authApiInterface = this.retrofit.create(AuthApiInterface.class);
    }

    public void login(String email, String password, LoginCallback loginCallback){
        Call<LoginResponse> loginResponseCall
                 = this.authApiInterface.login(
                         new LoginRequest(email, password, this.androidId));

        try {
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.code()==200){
                        loginCallback.onSuccess(response.body());
                    }else if(response.code()==422){
                        try{
                            Gson gson = new Gson();
                            LoginResponse loginResponse
                                    = gson.fromJson(
                                            response.errorBody().string(), LoginResponse.class);
                            loginCallback.onError(new Throwable(loginResponse.getMessage()));
                        }catch (Throwable t){
                            loginCallback.onError(t);
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                    Log.i(TAG, "onFailure: login failure");
                    loginCallback.onError(throwable);
                }
            });
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    // #TODO: get user data by auth token
    public void getUserData(GetUserCallback userDataCallback){
        Call<UserModel> getUserDataCall
                = this.authApiInterface.getUser();

        try{
            getUserDataCall.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    switch (response.code()){
                        case 200:
                            userDataCallback.onSuccess(response.body());
                            break;
                        case 401:
                            userDataCallback.onSuccess(null);
                            break;
                        default:
                            try {
                                Gson gson = new Gson();
                                Response getUserDataResponse =  gson.fromJson(
                                        response.errorBody().toString(),
                                        Response.class);
                                userDataCallback.onError(new Throwable(getUserDataResponse.message()));
                            }catch (Throwable t){
                                Log.e(TAG, "onResponse: ", t);
                            }
                            break;
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }catch (Throwable t){
            t.printStackTrace();
        }

    }

//        callbacks
    public interface LoginCallback{
        void onSuccess(LoginResponse loginResponse);
        void onError(Throwable t);
    }

    public interface GetUserCallback{
        void onSuccess(UserModel user);
        void onError(Throwable t);
    }

//        interfaces
    public interface AuthApiInterface{
        @POST("login")
        Call<LoginResponse> login(@Body LoginRequest loginRequest);

        @GET("/user")
        Call<UserModel> getUser();
    }
}
