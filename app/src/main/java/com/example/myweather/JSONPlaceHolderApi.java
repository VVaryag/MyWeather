package com.example.myweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("data/2.5/weather?q={city}&units=metric&")
    public Call<Post> getPostWithCity(@Path("city") String city);
    public  Call<Post> getPostWithApi(@Query("api") String api);

}
