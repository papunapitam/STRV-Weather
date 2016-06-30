package com.minovotny.weather.android.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.minovotny.weather.android.R;
import com.minovotny.weather.android.databinding.ForecastItemBinding;
import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.viewModel.ForecastWeatherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.BindingHolder> {

    private List<WeatherModel> items;
    private Context context;
    private ForecastItemBinding binding;

    public ForecastListAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.forecast_item,
                parent,
                false);
        return new BindingHolder(context, binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindWeather(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<WeatherModel> posts) {
        items = posts;
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private Context context;
        private ForecastItemBinding binding;

        public BindingHolder(Context context, ForecastItemBinding binding) {
            super(binding.getRoot());
            this.context = context;
            this.binding = binding;
        }

        public void bindWeather(WeatherModel weather) {
            binding.setForecastWeatherViewModel(new ForecastWeatherViewModel(context, weather));
        }
    }
}