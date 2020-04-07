package com.example.myweather.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.lang.annotation.Inherited;
import java.util.List;
@Dao
public interface CityDao {

    @Query("SELECT * FROM cities")
    List<City> getCities();

    @Query("SELECT name_city FROM cities")
    List<String> getNameCities();

    @Insert
    void addCity(City city);
}
