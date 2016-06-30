package com.minovotny.weather.android.service.listener;

import com.minovotny.weather.android.model.WeatherModel;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public interface OnTodayWeatherLoadedListener {

    void todayWeatherLoaded(WeatherModel weather);

    void errorOccurred(String message);
}
