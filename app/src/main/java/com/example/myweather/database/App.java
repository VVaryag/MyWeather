package com.example.myweather.database;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;
    private  CitiesDatabase citiesDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        citiesDatabase = Room.
                databaseBuilder(this, CitiesDatabase.class, "base_cities")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static  App getInstance(){
        return instance;
    }

    public CitiesDatabase getCitiesDatabase(){
        return citiesDatabase;
    }
}
