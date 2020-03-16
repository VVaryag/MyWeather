package com.example.myweather.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.R;
import com.example.myweather.model.WeatherPOJO;
import com.example.myweather.network.JSONPlaceHolderApi;
import com.example.myweather.network.QueryWeather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityShow extends AppCompatActivity {



    public TextView view_town, view_date, view_temperature,
                    view_wind_direction, view_wind_speed,
                    view_pressure, view_humidity, sky_condition;
    public ImageView view_sky_condition;


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
        sky_condition = findViewById(R.id.sky_condition);
        view_sky_condition = findViewById(R.id.view_sky_condition);
        query(town);
        view_date.setText(setDate());

    }


    public void query(String town) {
        Log.d(DataClass.TAG2, town);
        Log.d(DataClass.TAG, "Start query");
        QueryWeather.getInstance().getJSONApi()
                .getUrlData(town, "metric", JSONPlaceHolderApi.api).enqueue(new Callback<WeatherPOJO>() {

            @Override
            public void onResponse(@NonNull Call<WeatherPOJO> call, @NonNull Response<WeatherPOJO> response) {
                Log.d(DataClass.TAG, "Start Response");
                if (response.isSuccessful()) {
                    WeatherPOJO weatherPOJO = response.body();

                    view_temperature.setText(weatherPOJO.getMain().getTemp());
                    view_pressure.setText(weatherPOJO.getMain().getPressure());
                    view_humidity.setText(weatherPOJO.getMain().getHumidity());
                    view_wind_direction.setText(weatherPOJO.getWind().getDeg());
                    view_wind_speed.setText(weatherPOJO.getWind().getSpeed());
                    sky_condition.setText(weatherPOJO.getWeatherBase().get(0).getDescription());

                    String icon = weatherPOJO.getWeatherBase().get(0).getIcon();
                    Picasso.get().load("https://openweathermap.org/img/wn/" + icon + "@2x.png").into(view_sky_condition);


                    Log.d(DataClass.TAG, weatherPOJO.toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherPOJO> call, Throwable t) {
                Log.e(DataClass.TAG, call.toString());
            }
        });
    }

    private String setDate (){
        String date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        return date;
    }

}


