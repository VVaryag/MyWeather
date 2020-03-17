package com.example.myweather.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.R;
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

    private String OPEN_WEATHER_MAP_IMAGE = "enter_town";
    private TextView viewTown, viewDate, view_temperature,
            view_wind_direction, view_wind_speed,
            view_pressure, view_humidity, sky_condition;
    private ImageView view_sky_condition;
    private String town = "kyiv";
    private Spinner enter_town;
    private ProgressBar progressBar;
    private RelativeLayout mainLayout;

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
        enter_town = findViewById(R.id.spinner_city);

        progressBar = findViewById(R.id.progress_bar);
        mainLayout = findViewById(R.id.main_layout);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Repository.citys);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enter_town.setAdapter(adapter);
        enter_town.setOnItemSelectedListener(onItemSelectedListener());

        viewTown.setText(town);
        viewDate = findViewById(R.id.view_date);
        view_temperature = findViewById(R.id.view_temperature);
        view_wind_direction = findViewById(R.id.view_wind_directions);
        view_wind_speed = findViewById(R.id.view_wind_speed);
        view_pressure = findViewById(R.id.view_pressure);
        view_humidity = findViewById(R.id.view_humidity);
        sky_condition = findViewById(R.id.sky_condition);
        view_sky_condition = findViewById(R.id.view_sky_condition);
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
        updateDate();

        view_temperature.setText(weather.getMain().getTemp());
        view_pressure.setText(weather.getMain().getPressure());
        view_humidity.setText(weather.getMain().getHumidity());
        view_wind_direction.setText(weather.getWind().getDeg());
        view_wind_speed.setText(weather.getWind().getSpeed());
        sky_condition.setText(weather.getWeatherBase().get(0).getDescription());

        String icon = weather.getWeatherBase().get(0).getIcon();
        Picasso.get().load(OPEN_WEATHER_MAP_IMAGE + icon + "@2x.png").into(view_sky_condition);

        setUILoadingState(false);

        Log.d(DataClass.TAG, weather.toString());
    }

    private String updateDate() {
        String date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        viewDate.setText(date);
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


