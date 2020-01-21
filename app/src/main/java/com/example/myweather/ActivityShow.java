package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.lang.String;


public class ActivityShow extends AppCompatActivity {


    public static TextView view_town, view_date, view_temperature, view_wind_direction, view_wind_speed, view_pressure, view_humidity,
            view_sky_condition, view_precipitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        view_town = (TextView) findViewById(R.id.view_town);
        Bundle input = getIntent().getExtras();
        String town = input.get("myTown").toString();
        view_town.setText(town);
        view_date = (TextView) findViewById(R.id.view_date);
        view_temperature = (TextView) findViewById(R.id.view_temperature);
        view_wind_direction = (TextView) findViewById(R.id.view_wind_directions);
        view_wind_speed = (TextView) findViewById(R.id.view_wind_speed);
        view_pressure = (TextView) findViewById(R.id.view_pressure);
        view_humidity = (TextView) findViewById(R.id.view_humidity);
        view_sky_condition = (TextView) findViewById(R.id.view_sky_condition);
        view_precipitation = (TextView) findViewById(R.id.view_precipitation);
        new QueryWeather().execute();
    }

    public void show_icons() {


    }

}

