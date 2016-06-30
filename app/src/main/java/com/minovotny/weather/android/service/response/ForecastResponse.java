package com.minovotny.weather.android.service.response;

import com.google.gson.annotations.SerializedName;
import com.minovotny.weather.android.service.response.items.City;
import com.minovotny.weather.android.service.response.items.ListData;

import java.util.ArrayList;

/**
 * Created by miroslav.novotny on 30.6.2016.
 */
public class ForecastResponse {

    private City city;
    @SerializedName("cod")
    private String cod;
    private String message;
    @SerializedName("cnt")
    private Integer cnt;
    @SerializedName("list")
    private ArrayList<ListData> list;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public ArrayList<ListData> getList() {
        return list;
    }

    public void setList(ArrayList<ListData> list) {
        this.list = list;
    }

}
