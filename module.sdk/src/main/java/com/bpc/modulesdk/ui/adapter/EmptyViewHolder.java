package com.bpc.modulesdk.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.bpc.mobilebanksdk.R;

public class EmptyViewHolder extends RecyclerView.ViewHolder {
    public TextView emptyText;

    public EmptyViewHolder(View itemView) {
        super(itemView);
        emptyText = (TextView) itemView.findViewById(R.id.title);
    }
}