package com.example.myweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.String;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityShow extends AppCompatActivity {


    public static TextView view_town, view_date, view_temperature, view_wind_direction, view_wind_speed, view_pressure, view_humidity,
            view_sky_condition, view_precipitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        view_town = findViewById(R.id.view_town);
        Bundle input = getIntent().getExtras();
        String town = input.get("myTown").toString();
String api = "appid=8118ed6ee68db2debfaaa5a44c832918";
        view_town.setText(town);
        view_date = findViewById(R.id.view_date);
        view_temperature = findViewById(R.id.view_temperature);
        view_wind_direction = findViewById(R.id.view_wind_directions);
        view_wind_speed = findViewById(R.id.view_wind_speed);
        view_pressure = findViewById(R.id.view_pressure);
        view_humidity = findViewById(R.id.view_humidity);
        view_sky_condition = findViewById(R.id.view_sky_condition);
        view_precipitation = findViewById(R.id.view_precipitation);


        QueryWeather.getInstance().getJSONApi().getPostWithApi(api).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                Post post = response.body();

                view_temperature.setText(post.getDescription());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }


}

