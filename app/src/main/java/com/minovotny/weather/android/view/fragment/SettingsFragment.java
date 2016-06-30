package com.minovotny.weather.android.view.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.minovotny.weather.android.R;

/**
 * Created by Miroslav Novotny on 19.06.2016.
 */
public class SettingsFragment extends PreferenceFragment {

    public static final String KEY_PREF_DISTANCE_UNIT = "pref_length_units";
    public static final String KEY_PREF_TEMPERATURE_UNIT = "pref_temperature_unit";

    public SettingsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        addPreferencesFromResource(R.xml.prefs);
    }


}
