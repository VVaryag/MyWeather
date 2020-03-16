package com.example.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myweather.R;


public class ActivityHello extends AppCompatActivity {
    public static String town = "" ;
    Spinner enter_town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        enter_town = findViewById(R.id.enter_town);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, DataClass.city);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enter_town.setAdapter(adapter);
        enter_town.setOnItemSelectedListener(onItemSelectedListener());
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               town = parent.getItemAtPosition(position).toString();
                show_weather(town);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                finish();
            }

        };

    }


    public void show_weather(String town) {
        Intent weather_in_town = new Intent(this, ActivityShow.class);
        weather_in_town.putExtra("myTown", town);

        startActivity(weather_in_town);

    }


}


