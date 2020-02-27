package com.example.myweather.network;


import com.example.myweather.model.Weather;
import com.example.myweather.model.WeatherPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    String api = "8118ed6ee68db2debfaaa5a44c832918";

    @GET("data/2.5/weather")
    Call<WeatherPOJO> getUrlData(@Query("q") String town, @Query("units") String units, @Query("appid") String api);
    Call<WeatherPOJO> getUrlGeo(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String api);

}
