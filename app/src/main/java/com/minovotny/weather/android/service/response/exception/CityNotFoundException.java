package com.minovotny.weather.android.service.response.exception;

import com.minovotny.weather.android.R;
import com.minovotny.weather.android.WeatherApp;

/**
 * Created by miroslav.novotny on 30.6.2016.
 */
public class CityNotFoundException extends Exception {
    public CityNotFoundException() {
        super(WeatherApp.getContext().getString(R.string.error_city_not_found));
    }
}

