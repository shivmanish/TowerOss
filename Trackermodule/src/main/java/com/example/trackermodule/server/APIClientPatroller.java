package com.example.trackermodule.server;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientPatroller {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://fibeross.net")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://49.50.77.81:8686")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }
}