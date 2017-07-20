package com.bpc.modulesdk.ui.adapter;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.bpc.modulesdk.utils.ListItem;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class TitleViewHolder extends RecyclerView.ViewHolder {

    private TextView textTitle;

    public TitleViewHolder(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.list_item_title, null));
        textTitle = (TextView) itemView.findViewById(R.id.title);
    }

    public void setTitle(ListItem listItem){
        setTitle(listItem.getTitle());
    }

    public void setTitle(String title){
        textTitle.setText(title);
    }

    public void setTitle(@StringRes int title){
        textTitle.setText(title);
    }
}
