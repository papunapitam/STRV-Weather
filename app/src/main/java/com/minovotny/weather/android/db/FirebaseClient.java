package com.minovotny.weather.android.db;

import com.firebase.client.Firebase;
import com.minovotny.weather.android.model.WeatherModel;

import java.util.List;

/**
 * Created by Miroslav Novotny on 19.06.2016.
 */
public class FirebaseClient {

    public final String URL_DB = "https://strv-weather-b288a.firebaseio.com/";
    public final String CHILD_TODAY = "today";
    public final String CHILD_FORECAST = "forecast";

    private Firebase database;

    private static FirebaseClient INSTANCE;

    public static FirebaseClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseClient();
        }
        return INSTANCE;
    }

    private FirebaseClient() {
        database = new Firebase(URL_DB);
    }

    public void saveTodayWeather(WeatherModel weatherModel) {
        database.child(CHILD_TODAY).setValue(weatherModel);
    }

    public void saveForecastWeather(List<WeatherModel> forecast) {
        database.child(CHILD_FORECAST).setValue(forecast);
    }
}
