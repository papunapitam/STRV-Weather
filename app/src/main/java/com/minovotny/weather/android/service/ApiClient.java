package com.minovotny.weather.android.service;

import com.minovotny.weather.android.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Miroslav Novotny on 21.06.2016.
 */
public class ApiClient {

    private static Retrofit retrofit;

    private ApiClient(){
    }

    public static Retrofit retrofit(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_WEATHER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
