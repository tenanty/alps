package com.channelsoft.alps.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.to.Settings;

import java.util.List;

/**
 * Created by yuanshun on 2015/1/15.
 */
public class SettingsAdapter extends ArrayAdapter<Settings> {
    private int resourceId;

    public SettingsAdapter(Context context, int resource, List<Settings> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Settings settings = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        ImageView image = (ImageView) view.findViewById(R.id.settings_img);
        TextView name = (TextView) view.findViewById(R.id.settings_id);

        image.setImageResource(settings.getSettingsImg());
        name.setText(settings.getSettingsName());

        return view;
    }
}
