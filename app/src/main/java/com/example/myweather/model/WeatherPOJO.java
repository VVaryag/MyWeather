package com.example.myweather.model;

import androidx.annotation.NonNull;

import java.util.List;

public class WeatherPOJO {
    private Coord coord;
    private List<WeatherBase> weather;
    private String base;
    private Main main;
    private String visibility;
    private Wind wind;
    private Clouds clouds;
    private String dt;
    private Sys sys;
    private String timezone;
    private String id;
    private String name;
    private String cod;
    //    private WeatherBase[] weatherBase;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<WeatherBase> getWeatherBase() {
        return weather;
    }

    public void setWeatherBase(List<WeatherBase> weatherBase) {
        this.weather = weatherBase;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nClassPojo \n[visibility = " + visibility + ", timezone = " + timezone +
                ", main = " + main + ", clouds = " + clouds + ", sys = " + sys + ", dt = " + dt +
                ", coord = " + coord + ", name = " + name + ", cod = " +
                cod + ", id = " + id + ", base = " + base + ", wind = " + wind + "]";
    }
}
