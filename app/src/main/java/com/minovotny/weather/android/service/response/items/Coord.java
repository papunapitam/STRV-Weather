package com.minovotny.weather.android.service.response.items;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miroslav.novotny on 30.6.2016.
 */
public class Coord {

    @SerializedName("lon")
    private Double longitude;
    @SerializedName("lat")
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double lon) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
