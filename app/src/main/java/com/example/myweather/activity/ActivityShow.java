package com.example.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myweather.R;
import com.example.myweather.model.WeatherPOJO;
import com.example.myweather.network.JSONPlaceHolderApi;

import java.lang.String;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ActivityShow extends AppCompatActivity {
    public static final String TAG = "retrofit";

    private String api = "appid=8118ed6ee68db2debfaaa5a44c832918";

    public TextView view_town, view_date, view_temperature, view_wind_direction, view_wind_speed, view_pressure, view_humidity,
            view_sky_condition, view_precipitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        view_town = findViewById(R.id.view_town);
        Bundle input = getIntent().getExtras();
//        String town = input.get("myTown").toString();

//        view_town.setText(town);
        view_date = findViewById(R.id.view_date);
        view_temperature = findViewById(R.id.view_temperature);
        view_wind_direction = findViewById(R.id.view_wind_directions);
        view_wind_speed = findViewById(R.id.view_wind_speed);
        view_pressure = findViewById(R.id.view_pressure);
        view_humidity = findViewById(R.id.view_humidity);
        view_sky_condition = findViewById(R.id.view_sky_condition);
        view_precipitation = findViewById(R.id.view_precipitation);

       /* QueryWeather.getInstance()
                .getJSONApi()
                .getPostWithApi(api)
                .enqueue(new Callback<WeatherPOJO>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherPOJO> call, @NonNull Response<WeatherPOJO> response) {
                        WeatherPOJO post = response.body();
                        view_temperature.setText(post.getDescription());
                    }

                    @Override
                    public void onFailure(Call<WeatherPOJO> call, Throwable t) {
                    }
                });*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceHolderApi service = retrofit.create(JSONPlaceHolderApi.class);

        service.getUrlData().enqueue(new Callback<WeatherPOJO>() {
            @Override
            public void onResponse(Call<WeatherPOJO> call, Response<WeatherPOJO> response) {
                if (response.isSuccessful()) {
                    WeatherPOJO weatherPOJO = response.body();

                    Log.d(TAG, weatherPOJO.toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherPOJO> call, Throwable t) {
                Log.e(TAG, call.toString());
            }
        });
    }
}

