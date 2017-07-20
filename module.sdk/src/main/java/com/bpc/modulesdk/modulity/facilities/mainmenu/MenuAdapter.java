package com.bpc.modulesdk.modulity.facilities.mainmenu;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.bpc.mobilebanksdk.R;

public class MenuAdapter extends ArrayAdapter<MainMenuItem> {

    public MenuAdapter(Context context, List<MainMenuItem> objects) {
        super(context, R.layout.menu_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainMenuItem item = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (item.getImageId() <= 0) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_title_item, null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(item.getTitleId());
            convertView.setOnClickListener(null);
        } else {
            convertView = inflater.inflate(R.layout.menu_item, null);
            TextView title = (TextView) convertView.findViewById(R.id.item_title);
            ImageView icon = (ImageView) convertView.findViewById(R.id.item_icon);
            title.setText(item.getTitleId());
            icon.setImageResource(item.getImageId());
        }
        return convertView;
    }
}