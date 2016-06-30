package com.minovotny.weather.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.minovotny.weather.android.R;
import com.minovotny.weather.android.view.fragment.SettingsFragment;

/**
 * Created by miroslav.novotny on 28.6.2016.
 */
public class SettingsHelper implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static SettingsHelper INSTANCE;

    private Context context;
    private String distanceUnit;
    private String tempUnit;

    private SettingsHelper(Context context) {
        this.context = context;
        loadSavedSettings();
        PreferenceManager.getDefaultSharedPreferences(this.context).registerOnSharedPreferenceChangeListener(this);
    }

    public static SettingsHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SettingsHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadSavedSettings();
    }

    public String getTemperatureString(float temperature) {
        if (tempUnit.equalsIgnoreCase("Fahrenheit")) {
            return Math.round(UnitConverter.convertCelsiusToFahrenheit(temperature)) + context.getString(R.string.unit_fahrenheit);
        } else {
            return Math.round(temperature) + context.getString(R.string.unit_celsius);
        }
    }

    public String getWindSpeedString(float windSpeed) {
        if (distanceUnit.equalsIgnoreCase("Kilometer")) {
            return Math.round(windSpeed) + " " + context.getString(R.string.wind_km_h);
        } else {
            return Math.round(UnitConverter.convertKphToMis(windSpeed)) + " " + context.getString(R.string.wind_m_s);
        }
    }

    private void loadSavedSettings() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        distanceUnit = sharedPref.getString(SettingsFragment.KEY_PREF_DISTANCE_UNIT, context.getString(R.string.pref_length_unit_default_value));
        tempUnit = sharedPref.getString(SettingsFragment.KEY_PREF_TEMPERATURE_UNIT, context.getString(R.string.pref_temperature_unit_default_value));
    }
}
