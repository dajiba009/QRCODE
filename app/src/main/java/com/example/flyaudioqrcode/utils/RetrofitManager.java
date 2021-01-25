package com.example.flyaudioqrcode.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static RetrofitManager sManager = null;
    private final Retrofit mRetrofit;

    private RetrofitManager(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitManager getInstance(){
        if(sManager == null){
            synchronized (RetrofitManager.class){
                if(sManager == null){
                    sManager = new RetrofitManager();
                }
            }
        }
        return sManager;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
