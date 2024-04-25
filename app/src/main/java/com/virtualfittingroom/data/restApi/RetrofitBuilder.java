package com.virtualfittingroom.data.restApi;

import android.content.Context;
import android.util.Log;

import com.virtualfittingroom.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static final String TAG = "RetrofitBuilder";
    public static String BASE_URL;
    public String TOKEN = "";
    OkHttpClient okHttpClient;
    Retrofit retrofit;
    Context context;

//    Build retrofit from string

    public RetrofitBuilder(String TOKEN, Context context) {
        this.TOKEN = TOKEN;
        this.context = context;
        this.BASE_URL = context.getString(R.string.BASE_URL);
    }

    public Retrofit build(){

        // Build new okHttpClient
        this.okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("Authorization", "Bearer "+TOKEN)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

//        Build new Retrofit
        this.retrofit = new Retrofit.Builder()
                .baseUrl(this.BASE_URL)
                .client(this.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d(TAG, "build: "+retrofit.baseUrl().toString());
        return this.retrofit;
    }
}
