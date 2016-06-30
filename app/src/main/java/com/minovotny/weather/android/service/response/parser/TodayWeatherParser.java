package com.minovotny.weather.android.service.response.parser;

import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.service.response.TodayWeatherResponse;
import com.minovotny.weather.android.service.response.exception.ApiException;
import com.minovotny.weather.android.service.response.exception.CityNotFoundException;

import java.util.Date;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public class TodayWeatherParser {

    public static final String HTML_OK = "200";
    public static final String HTML_NOT_FOUND = "404";

    public static WeatherModel parse(TodayWeatherResponse response) throws CityNotFoundException, ApiException {

        if(!response.getCod().equals(HTML_OK)) {
            if(response.getCod().equals(HTML_NOT_FOUND)) {
                throw new CityNotFoundException();
            } else {
                throw new ApiException();
            }
        }

        float temperature;
        String icon;
        float windSpeed;
        float windDegrees;
        String cityName;
        String country;
        Date date;
        String weatherCondition;
        String weatherConditionDescription;
        int humidity;
        float pressure;
        float rain = 0;

        // parse data
        cityName = response.getName();
        date = new Date();
        temperature = response.getMain().getTemperature().floatValue();
        pressure = response.getMain().getPressure().floatValue();
        humidity = response.getMain().getHumidity();
        weatherCondition = response.getWeather().get(0).getMain();
        weatherConditionDescription = response.getWeather().get(0).getDescription();
        icon = response.getWeather().get(0).getIcon();
        windSpeed = response.getWind().getSpeed().floatValue();
        windDegrees = response.getWind().getDegree();
        country = response.getSys().getCountry();
        if (response.getRain() != null) {
            if (response.getRain().getLastThreeHours() != null) {
                rain = response.getRain().getLastThreeHours().floatValue();
            }
        }

        WeatherModel model = new WeatherModel();
        model.setTemperature(temperature);
        model.setCityName(cityName);
        model.setCountry(country);
        model.setHumidity(humidity);
        model.setIcon(icon);
        model.setPressure(pressure);
        model.setRain(rain);
        model.setWeatherCondition(weatherCondition);
        model.setWeatherConditionDescription(weatherConditionDescription);
        model.setTimestamp(date);
        model.setWindDegrees(windDegrees);
        model.setWindSpeed(windSpeed);

        return model;
    }

}
