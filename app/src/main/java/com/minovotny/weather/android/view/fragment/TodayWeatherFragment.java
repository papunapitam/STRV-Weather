package com.minovotny.weather.android.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.minovotny.weather.android.R;
import com.minovotny.weather.android.databinding.FragmentTodayWeatherBinding;
import com.minovotny.weather.android.db.FirebaseClient;
import com.minovotny.weather.android.model.WeatherModel;
import com.minovotny.weather.android.service.listener.OnTodayWeatherLoadedListener;
import com.minovotny.weather.android.service.OpenWeather;
import com.minovotny.weather.android.utils.ConnectivityUtils;
import com.minovotny.weather.android.utils.LocationHelper;
import com.minovotny.weather.android.view.activity.MainActivity;
import com.minovotny.weather.android.viewModel.TodayWeatherViewModel;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by Miroslav Novotny on 09.06.2016.
 */
public class TodayWeatherFragment extends Fragment implements OnTodayWeatherLoadedListener,
        SwipeRefreshLayout.OnRefreshListener, Observer, MainActivity.OnChangeLocation {

    private FragmentTodayWeatherBinding fragmentTodayWeatherBinding;
    private SwipeRefreshLayout swipeToRefreshLayout;
    private TodayWeatherViewModel todayWeatherViewModel;
    private Context context;
    private String location;

    private static TodayWeatherFragment INSTANCE = null;

    public static TodayWeatherFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TodayWeatherFragment();
        }
        return INSTANCE;
    }

    public TodayWeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        Firebase.setAndroidContext(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentTodayWeatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false);

        todayWeatherViewModel = new TodayWeatherViewModel(context, new WeatherModel());
        fragmentTodayWeatherBinding.setTodayWeatherViewModel(todayWeatherViewModel);

        swipeToRefreshLayout = fragmentTodayWeatherBinding.swipeContainer;
        swipeToRefreshLayout.setOnRefreshListener(this);

        LocationHelper.getInstance().addObserver(this);

        loadLocationData();
        loadDataFromApi();
        return fragmentTodayWeatherBinding.getRoot();

    }

    @Override
    public void todayWeatherLoaded(WeatherModel model) {
        swipeToRefreshLayout.setRefreshing(false);
        FirebaseClient.getInstance().saveTodayWeather(model);
        if(((MainActivity)getActivity()) != null && ((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity)getActivity()).getSupportActionBar().setSubtitle(model.getCityName());
        }
        todayWeatherViewModel.setModel(model);
    }

    @Override
    public void errorOccurred(String message) {
        swipeToRefreshLayout.setRefreshing(false);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
            activity.onSectionAttached(MainActivity.TODAY);
        }
    }

    public void loadDataFromApi() {
        if (ConnectivityUtils.isNetworkAvailable()) {
            OpenWeather.getTodayWeatherData(this, location);
        } else {
            Toast.makeText(context, context.getString(R.string.error_no_network), Toast.LENGTH_SHORT).show();
        }
    }

    public void loadLocationData() {
        location = LocationHelper.getInstance().getLocation();
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
