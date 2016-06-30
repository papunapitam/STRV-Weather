package com.minovotny.weather.android;

import android.app.Application;
import android.content.Context;

/**
 * Created by Miroslav Novotny on 30.06.2016.
 */
public class WeatherApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        WeatherApp.context = getApplicationContext();
    }

    public static Context getContext() {
        return WeatherApp.context;
    }
}
