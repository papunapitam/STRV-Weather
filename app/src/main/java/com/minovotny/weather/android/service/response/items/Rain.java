package com.minovotny.weather.android.service.response.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by miroslav.novotny on 30.6.2016.
 */
public class Rain {

    @SerializedName("3h")
    @Expose
    private Float lastThreeHours;

    public Float getLastThreeHours() {
        return lastThreeHours;
    }

    public void setLastThreeHours(Float lastThreeHours) {
        this.lastThreeHours = lastThreeHours;
    }

}
