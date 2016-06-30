package com.minovotny.weather.android.utils;

import android.text.TextUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Miroslav Novotny on 20.06.2016.
 */
public class LocationHelper extends Observable {

    private static String location = "Prague";     // default value set to Prague

    private static LocationHelper INSTANCE;

    public static LocationHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocationHelper();
        }
        return INSTANCE;
    }

    public LocationHelper() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String newLocation) {
        if (!TextUtils.isEmpty(newLocation) || newLocation != null) {
            location = newLocation;
            setChanged();
            notifyObservers(location);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }

    @Override
    public synchronized void deleteObserver(Observer observer) {
        super.deleteObserver(observer);
    }
}
