package com.minovotny.weather.android.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.minovotny.weather.android.R;
import com.minovotny.weather.android.WeatherApp;

/**
 * Created by Miroslav Novotny on 19.06.2016.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String PREF_LENGTH_UNIT = "pref_length_unit";
    public static final String PREF_TEMP_UNIT = "pref_temp_unit";

    public SettingsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        addPreferencesFromResource(R.xml.prefs);
        PreferenceManager.getDefaultSharedPreferences(WeatherApp.getContext()).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        getPreferenceScreen().removeAll();
        addPreferencesFromResource(R.xml.prefs);
    }
}
