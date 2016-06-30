package com.minovotny.weather.android.model;

import java.util.Date;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public class WeatherModel {

    private long id;
    private float temperature;
    private String icon;
    private float windSpeed;
    private float windDegrees;
    private String cityName;
    private String country;
    private Date timestamp;
    private String weatherCondition;
    private String weatherConditionDescription;
    private int humidity;
    private float pressure;
    private float rain;

    public WeatherModel() {
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public float getTemperature() {
        return temperature;
    }


    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public float getWindSpeed() {
        return windSpeed;
    }


    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }


    public float getWindDegrees() {
        return windDegrees;
    }


    public void setWindDegrees(float windDegrees) {
        this.windDegrees = windDegrees;
    }


    public String getCityName() {
        return cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public Date getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public String getWeatherCondition() {
        return weatherCondition;
    }


    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }


    public String getWeatherConditionDescription() {
        return weatherConditionDescription;
    }


    public void setWeatherConditionDescription(String weatherConditionDescription) {
        this.weatherConditionDescription = weatherConditionDescription;
    }


    public int getHumidity() {
        return humidity;
    }


    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }


    public float getPressure() {
        return pressure;
    }


    public void setPressure(float pressure) {
        this.pressure = pressure;
    }


    public float getRain() {
        return rain;
    }


    public void setRain(float rain) {
        this.rain = rain;
    }


    @Override
    public String toString() {
        return "WeatherModel{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", icon='" + icon + '\'' +
                ", windSpeed=" + windSpeed +
                ", windDegrees=" + windDegrees +
                ", cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", timestamp=" + timestamp +
                ", weatherCondition='" + weatherCondition + '\'' +
                ", weatherConditionDescription='" + weatherConditionDescription + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", rain=" + rain +
                '}';
    }
}
