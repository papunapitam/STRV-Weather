package com.minovotny.weather.android.service.response.items;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by miroslav.novotny on 30.6.2016.
 */
public class ListData {

    @SerializedName("dt")
    private Integer dt;
    @SerializedName("temp")
    private Temp temp;
    private Double pressure;
    private Integer humidity;
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    private Double speed;
    @SerializedName("deg")
    private Integer deg;
    private Integer clouds;
    private Double rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

}
