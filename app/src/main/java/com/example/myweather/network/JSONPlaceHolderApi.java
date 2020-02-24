package com.example.myweather.network;

import android.util.Log;

import com.example.myweather.model.WeatherPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
//&units=metric&appid=8118ed6ee68db2debfaaa5a44c832918
public interface JSONPlaceHolderApi {
//&units=metric&appid=8118ed6ee68db2debfaaa5a44c832918
 String api = "8118ed6ee68db2debfaaa5a44c832918";
    @GET("data/2.5/weather")
    public Call<WeatherPOJO> getUrlData(@Query("q") String town, @Query("units") String units, @Query("appid") String api);


}
