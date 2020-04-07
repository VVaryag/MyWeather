package com.example.myweather.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class City {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    public void setId(long id) {
        this.id = id;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnInfo(name = "city_id")
    private long cityId;

    @ColumnInfo(name = "name_city")
    private String name;

    public City(String name) {
        this.cityId = cityId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }
}
