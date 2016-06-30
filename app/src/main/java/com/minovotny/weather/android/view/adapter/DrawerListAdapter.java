package com.minovotny.weather.android.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minovotny.weather.android.R;

/**
 * Created by Miroslav Novotny on 09.06.2016.
 */
public class DrawerListAdapter extends BaseAdapter {

    private Context context;
    private String[] items;
    private int[] imageResources;

    public DrawerListAdapter(Context context) {
        this.context = context;
        initializeDefaultItems();
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_item, null);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.setDrawerItem(items[position], imageResources[position]);
        return convertView;
    }

    private void initializeDefaultItems() {
        items = new String[] {
                context.getString(R.string.menu_today),
                context.getString(R.string.menu_forecast)
        };
        imageResources = new int[] {
                R.drawable.ic_today,
                R.drawable.ic_forecast,
        };
    }

    private class ViewHolder {
        private TextView text;
        private ImageView image;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.menu_txt);
            image = (ImageView) view.findViewById(R.id.menu_img);
        }

        public void setDrawerItem(String txt, int imageResource) {
            text.setText(txt);
            image.setImageResource(imageResource);
        }
    }
}
