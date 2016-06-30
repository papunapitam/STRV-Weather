package com.minovotny.weather.android.service.response.parser;

import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.service.response.ForecastResponse;
import com.minovotny.weather.android.service.response.items.ListData;
import com.minovotny.weather.android.service.response.exception.ApiException;
import com.minovotny.weather.android.service.response.exception.CityNotFoundException;
import com.minovotny.weather.android.utils.UnitConverter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public class ForecastParser {

    public static final String HTML_OK = "200";
    public static final String HTML_NOT_FOUND = "404";

    public static List<WeatherModel> parse(ForecastResponse response) throws CityNotFoundException, ApiException {

        if(!response.getCod().equals(HTML_OK)) {
            if(response.getCod().equals(HTML_NOT_FOUND)) {
                throw new CityNotFoundException();
            } else {
                throw new ApiException();
            }
        }

        List<WeatherModel> forecast = new LinkedList<>();

        String cityName = response.getCity().getName();
        String country = response.getCity().getCountry();

        for (int i = 0; i < response.getList().size(); i++) {
            forecast.add(parseWeather(response.getList().get(i), cityName, country));
        }

        return forecast;
    }


    private static WeatherModel parseWeather(ListData list, String cityName, String country) {

        float temperature;
        String icon;
        float windSpeed;
        float windDegrees;
        Date date;
        String weatherCondition;
        String weatherConditionDescription;
        int humidity;
        float pressure;
        float rain = 0;

        date = UnitConverter.convertLongToDate(list.getDt().longValue());
        temperature = list.getTemp().getDay().floatValue();
        pressure = list.getPressure().floatValue();
        humidity = list.getHumidity();
        weatherCondition = list.getWeather().get(0).getMain();
        weatherConditionDescription = list.getWeather().get(0).getDescription();
        icon = list.getWeather().get(0).getIcon();
        windSpeed = list.getSpeed().floatValue();
        windDegrees = list.getDeg().floatValue();
        if (list.getRain() != null) {
            rain = list.getRain().floatValue();
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
