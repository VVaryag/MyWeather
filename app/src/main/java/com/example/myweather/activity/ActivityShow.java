package com.example.myweather.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import com.example.myweather.R;
import com.example.myweather.database.CitiesDatabase;
import com.example.myweather.model.WeatherPOJO;
import com.example.myweather.network.JSONPlaceHolderApi;
import com.example.myweather.network.QueryWeather;
import com.example.myweather.repository.Repository;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityShow extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private String OPEN_WEATHER_MAP_IMAGE = "https://openweathermap.org/img/wn/";
    private TextView viewTown, viewDate, viewTemperature,
            viewWindDirection, viewWindSpeed,
            viewPressure, viewHumidity, skyCondition, humidity,
            windSpeed, pressure, windDirection;
    private ImageView viewSkyCondition;
    private String town = "kyiv";
    private Spinner enterTown;
    private ProgressBar progressBar;
    private ConstraintLayout mainLayout;

    AdapterView.OnItemSelectedListener onItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                town = parent.getItemAtPosition(position).toString();
                loadWeather(town);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        viewTown = findViewById(R.id.view_town);
        enterTown = findViewById(R.id.spinner_city);
        humidity = findViewById(R.id.humidity);
        windSpeed = findViewById(R.id.wind_speed);
        pressure = findViewById(R.id.pressure);
        windDirection = findViewById(R.id.wind_direction);
        progressBar = findViewById(R.id.progress_bar);
        mainLayout = findViewById(R.id.main_layout);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Repository.showCity());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enterTown.setAdapter(adapter);
        enterTown.setOnItemSelectedListener(onItemSelectedListener());


        viewDate = findViewById(R.id.view_date);
        viewTemperature = findViewById(R.id.view_temperature);
        viewWindDirection = findViewById(R.id.view_wind_directions);
        viewWindSpeed = findViewById(R.id.view_wind_speed);
        viewPressure = findViewById(R.id.view_pressure);
        viewHumidity = findViewById(R.id.view_humidity);
        skyCondition = findViewById(R.id.sky_condition);
        viewSkyCondition = findViewById(R.id.view_sky_condition);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadWeather(town);
    }

    public void loadWeather(String town) {
        Log.d(DataClass.TAG2, "Start query - town: " + town);

        setUILoadingState(true);
        QueryWeather.getInstance().getJSONApi()
                .getUrlData(town, "metric", JSONPlaceHolderApi.api)
                .enqueue(new Callback<WeatherPOJO>() {

                    @Override
                    public void onResponse(@NonNull Call<WeatherPOJO> call,
                                           @NonNull Response<WeatherPOJO> response) {
                        Log.d(DataClass.TAG, "Start Response");
                        if (response.isSuccessful()) {
                            WeatherPOJO weatherPOJO = response.body();

                            Log.d(DataClass.TAG, weatherPOJO.toString());

                            updateUI(weatherPOJO);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherPOJO> call, Throwable t) {
                        setUILoadingState(false);

                        Log.e(DataClass.TAG, call.toString());
                    }
                });
    }

    private void updateUI(WeatherPOJO weather) {

        viewDate.setText(updateDate());
        viewTemperature.setText(weather.getMain().getTemp());
        viewPressure.setText(weather.getMain().getPressure());
        viewHumidity.setText(weather.getMain().getHumidity());
        viewWindDirection.setText(weather.getWind().getDeg());
        viewWindSpeed.setText(weather.getWind().getSpeed());
        skyCondition.setText(weather.getWeatherBase().get(0).getDescription());
        viewTown.setText(weather.getName());
        pressure.setText("Pressure");
        humidity.setText("Humidity");
        windDirection.setText("Wind direction");
        windSpeed.setText("Wind speed");



        String icon = weather.getWeatherBase().get(0).getIcon();
        Picasso.get().load(OPEN_WEATHER_MAP_IMAGE + icon + "@2x.png").into(viewSkyCondition);

        setUILoadingState(false);

        Log.d(DataClass.TAG, weather.toString());
    }

    private String updateDate() {
        String date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        return date;
    }

    private void setUILoadingState(boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
        enableUI(mainLayout, !isShow);

    }

    private void enableUI(ViewGroup viewGroup, boolean enable) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setEnabled(enable);
        }
    }
}


