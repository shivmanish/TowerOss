package com.smarthub.baseapplication.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObjectInstance {

    private static Retrofit retrofit;
    public static String BASE_URL = "http://49.50.77.81:8686/";

    public static Retrofit getInstance(){
        Gson g = new GsonBuilder().setLenient().create();
        if (retrofit==null)
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(g))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofit;
    }

}
