package com.virtualfittingroom.data.restApi;

import com.google.gson.Gson;
import com.virtualfittingroom.data.model.LoggedInUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class AuthApi {
    Retrofit retrofit;
    String authToken;
    AuthApiInterface authApiInterface;

    public AuthApi(){
        this.retrofit = new RetrofitBuilder("").build();
        this.authApiInterface = this.retrofit.create(AuthApiInterface.class);
    }

//    build authApi by token
    public AuthApi(String authToken){
        this.authToken = authToken;
        this.retrofit = new RetrofitBuilder(this.authToken).build();
        this.authApiInterface = this.retrofit.create(AuthApiInterface.class);
    }

    public void login(String email, String password, String androidId, LoginCallback loginCallback){
        Call<LoginResponse> loginResponseCall
                 = this.authApiInterface.login(
                         new LoginRequest(email, password, androidId));

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
                            t.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                    loginCallback.onError(throwable);
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

//        interfaces
    public interface AuthApiInterface{
        @POST("auth/login")
        Call<LoginResponse> login(@Body LoginRequest loginRequest);
    }
}
