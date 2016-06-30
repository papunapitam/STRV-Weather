package com.minovotny.weather.android.service;

import android.util.Log;

import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.service.response.ForecastResponse;
import com.minovotny.weather.android.service.response.TodayWeatherResponse;
import com.minovotny.weather.android.service.response.exception.ApiException;
import com.minovotny.weather.android.service.response.exception.CityNotFoundException;
import com.minovotny.weather.android.service.listener.OnForecastLoadedListener;
import com.minovotny.weather.android.service.listener.OnTodayWeatherLoadedListener;
import com.minovotny.weather.android.service.response.parser.ForecastParser;
import com.minovotny.weather.android.service.response.parser.TodayWeatherParser;
import com.minovotny.weather.android.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Miroslav Novotny on 21.06.2016.
 */
public class OpenWeather {

    public interface OpenWeatherService {

        @GET("data/2.5/weather/")
        Call<TodayWeatherResponse> getTodayWeatherData(
                @Query("APPID") String appid,
                @Query("q") String q,
                @Query("units") String units);

        @GET("data/2.5/forecast/daily")
        Call<ForecastResponse> getForecastWeatherData(
                @Query("APPID") String appid,
                @Query("q") String q,
                @Query("units") String units,
                @Query("cnt") int cnt);

    }

    public static void getForecastWeatherData(final OnForecastLoadedListener listener, String location) {
        OpenWeatherService mService = ApiClient.retrofit().create(OpenWeatherService.class);
        mService.getForecastWeatherData(Constants.WEATHER_API_KEY, location, Constants.METRIC_UNIT, 16)
                .enqueue(new Callback<ForecastResponse>() {
                    @Override
                    public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                        ForecastResponse body = response.body();
                        List<WeatherModel> forecast;
                        try {
                            forecast = ForecastParser.parse(body);
                            listener.forecastWeatherLoaded(forecast);
                        } catch (CityNotFoundException | ApiException e) {
                            Log.e("Forecast onResponse: ", e.getMessage());
                            listener.errorOccurred(e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastResponse> call, Throwable t) {
                        listener.errorOccurred(t.getMessage());
                        Log.e("Forecast onFailure: ", t.getMessage());
                    }
                });
    }

    public static void getTodayWeatherData(final OnTodayWeatherLoadedListener listener, String location) {
        OpenWeatherService mService = ApiClient.retrofit().create(OpenWeatherService.class);
        mService.getTodayWeatherData(Constants.WEATHER_API_KEY, location, Constants.METRIC_UNIT)
                .enqueue(new Callback<TodayWeatherResponse>() {

                    @Override
                    public void onResponse(Call<TodayWeatherResponse> call, Response<TodayWeatherResponse> response) {
                        TodayWeatherResponse body = response.body();
                        WeatherModel model;
                        try {
                            model = TodayWeatherParser.parse(body);
                            listener.todayWeatherLoaded(model);
                        } catch (CityNotFoundException | ApiException e) {
                            Log.e("Today onResponse: ", e.getMessage());
                            listener.errorOccurred(e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<TodayWeatherResponse> call, Throwable t) {
                        listener.errorOccurred(t.getMessage());
                        Log.e("Today onFailure: ", t.getMessage());
                    }
                });
    }

}
