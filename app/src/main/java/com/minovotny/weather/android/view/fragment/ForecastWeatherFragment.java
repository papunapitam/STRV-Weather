package com.minovotny.weather.android.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.minovotny.weather.android.R;
import com.minovotny.weather.android.db.FirebaseClient;
import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.service.listener.OnForecastLoadedListener;
import com.minovotny.weather.android.service.OpenWeather;
import com.minovotny.weather.android.utils.ConnectivityUtils;
import com.minovotny.weather.android.utils.DividerItemDecoration;
import com.minovotny.weather.android.utils.LocationHelper;
import com.minovotny.weather.android.view.activity.MainActivity;
import com.minovotny.weather.android.view.adapter.ForecastListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Miroslav Novotny on 13.06.2016.
 */
public class ForecastWeatherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnForecastLoadedListener, MainActivity.OnChangeLocation, Observer {

    private SwipeRefreshLayout swipeToRefreshLayout;
    private RecyclerView recyclerView;
    private ForecastListAdapter forecastListAdapter;
    private List<WeatherModel> weatherModelList;
    private Context context;
    private String location;

    private static ForecastWeatherFragment INSTANCE = null;


    public static ForecastWeatherFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ForecastWeatherFragment();
        }
        return INSTANCE;
    }

    public ForecastWeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        forecastListAdapter = new ForecastListAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_forecast_weather, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.forecast_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context));

        weatherModelList = new ArrayList<>();
        forecastListAdapter.setItems(weatherModelList);

        recyclerView.setAdapter(forecastListAdapter);

        LocationHelper.getInstance().addObserver(this);

        swipeToRefreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.swipe_container_forecast);
        swipeToRefreshLayout.setOnRefreshListener(this);
        loadLocationData();
        loadDataFromApi();
        return fragmentView;
    }

    public void loadLocationData() {
        location = LocationHelper.getInstance().getLocation();
    }

    @Override
    public void onRefresh() {
        loadDataFromApi();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        if(activity != null) {
            activity.onSectionAttached(MainActivity.FORECAST);
        }
    }

    @Override
    public void forecastWeatherLoaded(List<WeatherModel> forecast) {
        swipeToRefreshLayout.setRefreshing(false);
        FirebaseClient.getInstance().saveForecastWeather(forecast);
        if(((MainActivity)getActivity()) != null && ((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity)getActivity()).getSupportActionBar().setSubtitle(forecast.get(0).getCityName());
        }
        forecastListAdapter.setItems(forecast);
    }

    @Override
    public void errorOccurred(String message) {
        swipeToRefreshLayout.setRefreshing(false);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void loadDataFromApi() {
        if (ConnectivityUtils.isNetworkAvailable()) {
            OpenWeather.getForecastWeatherData(this, location);
        } else {
            Toast.makeText(context, R.string.error_no_network, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        location = (String) data;
    }

    @Override
    public void changeLocation(String s) {
        loadDataFromApi();
    }
}

