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
        if(citiesDatabase == null){
        citiesDatabase = Room.
                databaseBuilder(this, CitiesDatabase.class, "base_cities")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
            CitiesDatabase cd = App.getInstance().getCitiesDatabase();
            CityDao cityDao = cd.cityDao();

            City town = new City("Kyiv");
            City town2 = new City ("Odesa");
            City town3 = new City("Kharkiv");
            City town4 = new City ("Chernihiv");
            City town5 = new City ("Kherson");
            City town6 = new City ("Mykolaiv");
            City town7 = new City ("Lviv");
            cityDao.addCity(town3);
            cityDao.addCity(town4);
            cityDao.addCity(town5);
            cityDao.addCity(town6);
            cityDao.addCity(town7);
            cityDao.addCity(town);
            cityDao.addCity(town2);
    }

    public static  App getInstance(){
        return instance;
    }

    public CitiesDatabase getCitiesDatabase(){
        return citiesDatabase;
    }
}
