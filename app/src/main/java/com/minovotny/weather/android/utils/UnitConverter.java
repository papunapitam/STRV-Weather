package com.minovotny.weather.android.utils;

import android.content.Context;

import com.minovotny.weather.android.R;

import java.util.Date;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public class UnitConverter {

    public static final long UNIX_TIME_CONSTANT = 1000l;

    public static String formatWindDirections(Context context, double windDirection) {
        String[] directions = context.getResources().getStringArray(R.array.wind_directions);
        return directions[(int) Math.round(((windDirection % 360) / 45)) % 8];
    }

    public static float convertCelsiusToFahrenheit(float temp) {
        return temp * (9f / 5f) + 32;
    }

    public static Date convertLongToDate(long time) {
        return new Date(time * UNIX_TIME_CONSTANT);
    }

    public static float convertKphToMis(float kph) {
        return kph / 3.6f;
    }
}
