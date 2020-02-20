package com.example.myweather.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueryWeather {
    private static QueryWeather mInstance;
    private static final  String BASE_URL = "https://api.openweathermap.org";
    private Retrofit mRetrofit;

    private QueryWeather(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static QueryWeather getInstance(){
        if (mInstance == null){
            mInstance = new QueryWeather();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi(){
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }
}