package com.minovotny.weather.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.minovotny.weather.android.WeatherApp;

/**
 * Created by miroslav.novotny on 28.6.2016.
 */
public class ConnectivityUtils {

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WeatherApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
