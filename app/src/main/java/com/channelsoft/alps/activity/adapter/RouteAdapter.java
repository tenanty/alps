package com.channelsoft.alps.activity.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.to.Route;

import java.util.List;

/**
 * Created by yuanshun on 2015/1/14.
 */
public class RouteAdapter extends ArrayAdapter<Route> {

    private int resourceId;

    public RouteAdapter(Context context, int textViewResourceId, List<Route> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Route route = getItem(position);//获取当前项的Route实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

        ImageView routeImage = (ImageView) view.findViewById(R.id.route_image);
        TextView routeName = (TextView) view.findViewById(R.id.route_name);

        routeImage.setImageResource(route.getImageId());
        routeName.setText(route.getName());

        return view;
    }
}
