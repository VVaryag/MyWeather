package com.example.myweather.repository;

import com.example.myweather.database.App;
import com.example.myweather.database.CitiesDatabase;
import com.example.myweather.database.City;
import com.example.myweather.database.CityDao;

import java.util.List;

public class Repository {
    public static String[] citys = new String[]{"Kyiv", "Kharkov", "Odessa"};


    public static List<String> showCity(){
        CitiesDatabase cd = App.getInstance().getCitiesDatabase();
        CityDao cityDao = cd.cityDao();
        List<String> cyties = cityDao.getNameCities();
        return cyties;

    }
    // TODO implement class helper that read city_list.json and work with it
}
