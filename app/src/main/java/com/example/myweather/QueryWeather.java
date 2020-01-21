package com.example.myweather;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QueryWeather extends AsyncTask<Void, Void, Void> {
    String temperature, w_direction, w_speed, pressure, humidity, sky_condition;
    String city = ActivityHello.set_town();


    @Override
    protected  Void doInBackground(Void... voids) {

        try {

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=8118ed6ee68db2debfaaa5a44c832918");
            HttpURLConnection weather_connection = (HttpURLConnection) url.openConnection();
            InputStream answer_connection = weather_connection.getInputStream();
            BufferedReader buffer_string = new BufferedReader(new InputStreamReader(answer_connection));

            String line = buffer_string.readLine();
            JSONObject reader = new JSONObject(line);
            JSONObject main = reader.getJSONObject("main");
            JSONObject weather = reader.getJSONArray("weather").getJSONObject(0);
            JSONObject wind = reader.getJSONObject("wind");

            temperature = main.getString("temp");
            pressure = main.getString("pressure");
            humidity = main.getString("humidity");
            sky_condition = weather.getString("description");
            w_speed = wind.getString("speed");
            w_direction = wind.getString("deg");
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        String date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());

        ActivityShow.view_temperature.setText(temperature + "°С");
        ActivityShow.view_wind_direction.setText(w_direction);
        ActivityShow.view_wind_speed.setText(w_speed);
        ActivityShow.view_humidity.setText(humidity);
        ActivityShow.view_pressure.setText(pressure);
        ActivityShow.view_sky_condition.setText(sky_condition);
        ActivityShow.view_date.setText(date);
    }


}
