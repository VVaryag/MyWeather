package com.example.myweather.model;

public class Clouds {
    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "\n ClassPojo:  \n[all = " + all + "]";
    }
}