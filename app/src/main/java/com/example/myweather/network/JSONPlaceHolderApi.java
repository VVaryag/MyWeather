package com.example.myweather.network;

import com.example.myweather.model.WeatherPOJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {
//    https://api.openweathermap.org/data/2.5/weather?q=Kiev&units=metric&appid=8118ed6ee68db2debfaaa5a44c832918

//    public Call<WeatherPOJO> getPostWithCity(@Path("city") String city);
//    public  Call<WeatherPOJO> getPostWithApi(@Query("api") String api);

    @GET("data/2.5/weather?q=Kiev&units=metric&appid=8118ed6ee68db2debfaaa5a44c832918")
    public Call<WeatherPOJO> getUrlData();

}
