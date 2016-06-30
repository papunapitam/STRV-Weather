package com.minovotny.weather.android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.minovotny.weather.android.R;

import com.minovotny.weather.android.dialog.AboutDialog;
import com.minovotny.weather.android.utils.LocationHelper;
import com.minovotny.weather.android.view.fragment.ForecastWeatherFragment;
import com.minovotny.weather.android.view.fragment.NavigationDrawerFragment;
import com.minovotny.weather.android.view.fragment.TodayWeatherFragment;

/**
 * Created by Miroslav Novotny on 15.06.2016.
 */
public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final int TODAY = 0;
    public static final int FORECAST = 1;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private OnChangeLocation onChangeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setSubtitle(LocationHelper.getInstance().getLocation());

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        switch (position) {
            case TODAY:
                fragment = TodayWeatherFragment.getInstance();
                break;
            case FORECAST:
                fragment = ForecastWeatherFragment.getInstance();
                break;
            default:
                fragment = TodayWeatherFragment.getInstance();
                break;
        }

        onChangeLocation = (OnChangeLocation) fragment;

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(getOnQueryTextListener(menu));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_about:
                new AboutDialog().show(getSupportFragmentManager(), MainActivity.class.getName());
                break;
            case R.id.action_settings:
                Intent settingsIntent = SettingsActivity.newIntent(this);
                startActivity(settingsIntent);
                break;
            case android.R.id.home:
                if(mNavigationDrawerFragment.isDrawerOpen()) {
                    mNavigationDrawerFragment.getDrawerLayout().closeDrawer(GravityCompat.START);
                } else {
                    mNavigationDrawerFragment.getDrawerLayout().openDrawer(GravityCompat.START);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationDrawerFragment.getDrawerToggle().syncState();
    }

    public SearchView.OnQueryTextListener getOnQueryTextListener(final Menu menu) {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String inputText) {
                LocationHelper.getInstance().setLocation(inputText);
                onChangeLocation.changeLocation(inputText);
                menu.findItem(R.id.menu_search).collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //get all text changes
                return false;
            }
        };
    }

    public void onSectionAttached(int section) {
        switch (section) {
            case TODAY:
                getSupportActionBar().setTitle(R.string.menu_today);
                break;
            case FORECAST:
                getSupportActionBar().setTitle(R.string.menu_forecast);
                break;
        }
    }

    public interface OnChangeLocation {

        void changeLocation(String s);
    }

}
