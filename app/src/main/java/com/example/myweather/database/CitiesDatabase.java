package com.example.myweather.database;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {City.class}, version = 2,exportSchema = false)
public abstract class CitiesDatabase  extends RoomDatabase {

    private static CitiesDatabase INSTANCE;

    public abstract CityDao cityDao();
    }

