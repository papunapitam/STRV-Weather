package com.minovotny.weather.android.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import org.apache.commons.lang3.text.WordUtils;

import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.utils.Constants;
import com.minovotny.weather.android.utils.SettingsHelper;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public class ForecastWeatherViewModel extends BaseObservable {

    private WeatherModel model;
    private SimpleDateFormat formatter;
    private SettingsHelper settingsHelper;

    public ForecastWeatherViewModel(Context context, WeatherModel model) {
        this.model = model;
        settingsHelper = SettingsHelper.getInstance(context);
        formatter = new SimpleDateFormat("EEE MMM dd", Locale.ENGLISH);
    }

    public void setModel(WeatherModel model) {
        this.model = model;
    }

    @Bindable
    public String getTimeStamp() {
        return formatter.format(model.getTimestamp());
    }

    @Bindable
    public String getTemp() {
        return settingsHelper.getTemperatureString(model.getTemperature());
    }

    @Bindable
    public String getDescription() {
        return WordUtils.capitalize(model.getWeatherConditionDescription());
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public String getImageUrl() {
        return Constants.ICON_URL + model.getIcon() + Constants.ICON_EXTENSION;
    }


}
