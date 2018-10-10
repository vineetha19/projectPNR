package com.vmax.railwayinfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by welcome on 2/28/2018.
 */

class RouteListAdapter extends BaseAdapter {

    ArrayList<RouteBean> bean = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public RouteListAdapter(RouteInfo placesList, ArrayList<RouteBean> bean) {
        this.bean = bean;
        context = placesList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_route_info, null);

            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.lat = convertView.findViewById(R.id.lat);
            viewHolder.lon = convertView.findViewById(R.id.lon);
            viewHolder.sch = convertView.findViewById(R.id.sch);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (bean != null && bean.size() > 0) {

            viewHolder.lat.setText("Latitude  :  "+bean.get(position).getLatitude());
            viewHolder.name.setText("Station Name  :  "+bean.get(position).getName());
            viewHolder.lon.setText("Longitude  :  "+bean.get(position).getLongitude());
            viewHolder.sch.setText("Sch Departure  :  "+bean.get(position).getSchdep());


        }

        return convertView;
    }


    private class ViewHolder {

        TextView name, lat, lon, sch;

    }
}
