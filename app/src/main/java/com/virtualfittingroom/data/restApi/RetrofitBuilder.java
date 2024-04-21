package com.virtualfittingroom.data.restApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static String BASE_API = "";
    public String TOKEN = "";
    OkHttpClient okHttpClient;
    Retrofit retrofit;

//    Build retrofit from string

    public RetrofitBuilder(String TOKEN) {
        this.TOKEN = TOKEN;
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
                .baseUrl(this.BASE_API)
                .client(this.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return this.retrofit;
    }
}
