package com.example.myweather.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myweather.R;

import com.example.myweather.model.WeatherPOJO;
import com.example.myweather.network.JSONPlaceHolderApi;
import com.example.myweather.network.QueryWeather;

import java.lang.String;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityShow extends AppCompatActivity {
    public static final String TAG = "retrofit";


    public TextView view_town, view_date, view_temperature,
            view_wind_direction, view_wind_speed, view_pressure, view_humidity,
            view_precipitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        view_town = findViewById(R.id.view_town);
        Bundle input = getIntent().getExtras();
        String town = input.get("myTown").toString();

        view_town.setText(town);
        view_date = findViewById(R.id.view_date);
        view_temperature = findViewById(R.id.view_temperature);
        view_wind_direction = findViewById(R.id.view_wind_directions);
        view_wind_speed = findViewById(R.id.view_wind_speed);
        view_pressure = findViewById(R.id.view_pressure);
        view_humidity = findViewById(R.id.view_humidity);
        view_precipitation = findViewById(R.id.view_precipitation);
        query(town);
    }

    public void query(String town) {

        Log.d(TAG, "Start query");


        QueryWeather.getInstance().getJSONApi()
                .getUrlData(town, "metric", JSONPlaceHolderApi.api).enqueue(new Callback<WeatherPOJO>() {

            @Override
            public void onResponse(@NonNull Call<WeatherPOJO> call, @NonNull Response<WeatherPOJO> response) {
                Log.d(TAG, "Start Response");
                if (response.isSuccessful()) {
                    WeatherPOJO weatherPOJO = response.body();

                    view_temperature.setText(weatherPOJO.getMain().getTemp());
                    view_pressure.setText(weatherPOJO.getMain().getPressure());
                    view_humidity.setText(weatherPOJO.getMain().getHumidity());
                    view_wind_direction.setText(weatherPOJO.getWind().getDeg());
                    view_wind_speed.setText(weatherPOJO.getWind().getSpeed());

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

