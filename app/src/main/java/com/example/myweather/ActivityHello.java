package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class ActivityHello extends AppCompatActivity {
    public static String town ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    public void show_weather(View view) {
        EditText enter_town = findViewById(R.id.enter_town);
        town = enter_town.getText().toString();
        Intent weather_in_town = new Intent(this, ActivityShow.class);
        weather_in_town.putExtra("myTown", town);
        startActivity(weather_in_town);
        new QueryWeather();


    }

    public static String set_town() {

        return town;
    }


}
