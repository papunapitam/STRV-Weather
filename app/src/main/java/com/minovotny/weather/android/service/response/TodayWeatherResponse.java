package com.minovotny.weather.android.service.response;

import com.google.gson.annotations.SerializedName;
import com.minovotny.weather.android.service.response.items.Clouds;
import com.minovotny.weather.android.service.response.items.Coord;
import com.minovotny.weather.android.service.response.items.Main;
import com.minovotny.weather.android.service.response.items.Rain;
import com.minovotny.weather.android.service.response.items.Sys;
import com.minovotny.weather.android.service.response.items.Weather;
import com.minovotny.weather.android.service.response.items.Wind;

import java.util.List;

/**
 * Created by miroslav.novotny on 30.6.2016.
 */
public class TodayWeatherResponse {

    @SerializedName("coord")
    private Coord coordinate;
    @SerializedName("weather")
    public List<Weather> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    @SerializedName("visibility")
    private Integer visibility;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("rain")
    private Rain rain;
    @SerializedName("dt")
    private Integer dt;
    @SerializedName("sys")
    private Sys sys;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private String cod;

    public Coord getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coord coordinate) {
        this.coordinate = coordinate;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

