package com.minovotny.weather.android.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.minovotny.weather.android.R;
import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.utils.Constants;
import com.minovotny.weather.android.utils.SettingsHelper;
import com.minovotny.weather.android.utils.UnitConverter;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by Miroslav Novotny on 17.06.2016.
 */
public class TodayWeatherViewModel extends BaseObservable {

    private Context context;
    private WeatherModel model;
    private SettingsHelper settingsHelper;

    public TodayWeatherViewModel(Context context, WeatherModel model) {
        this.context = context;
        this.model = model;
        settingsHelper = SettingsHelper.getInstance(context);
    }

    public void setModel(WeatherModel model) {
        this.model = model;
        notifyChange();
    }

    @Bindable
    public String getTemperature() {
        return settingsHelper.getTemperatureString(model.getTemperature());
    }

    @Bindable
    public String getWindSpeed() {
        return settingsHelper.getWindSpeedString(model.getWindSpeed());
    }

    @Bindable
    public String getWindDegrees() {
        return UnitConverter.formatWindDirections(context, model.getWindDegrees());
    }

    @Bindable
    public String getCityName() {
        return model.getCityName();
    }

    @Bindable
    public String getDescription() {
        return WordUtils.capitalize(model.getWeatherConditionDescription());
    }

    @Bindable
    public String getHumidity() {
        return model.getHumidity() + " " + context.getString(R.string.unit_humidity);
    }

    @Bindable
    public String getPressure() {
        return Math.round(model.getPressure()) + " " +  context.getString(R.string.unit_pressure);
    }

    @Bindable
    public String getRain() {
        return Math.round(model.getRain()) + " " +  context.getString(R.string.unit_rain);
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
