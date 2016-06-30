package com.minovotny.weather.android.service.listener;

import com.minovotny.weather.android.model.WeatherModel;

import java.util.List;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public interface OnForecastLoadedListener {

    void forecastWeatherLoaded(List<WeatherModel> weather);

    void errorOccurred(String message);

}
