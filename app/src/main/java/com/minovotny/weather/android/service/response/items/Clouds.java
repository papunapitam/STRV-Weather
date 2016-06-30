package com.minovotny.weather.android.service.response.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by miroslav.novotny on 30.6.2016.
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    public int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}
