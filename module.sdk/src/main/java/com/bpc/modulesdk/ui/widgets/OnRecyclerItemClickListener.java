package com.bpc.modulesdk.ui.widgets;

import android.view.View;

/**
 * Created by Samoylov on 13.01.2017.
 *
 * Listener for returning result after item click in RecyclerView
 */
public interface OnRecyclerItemClickListener<T> {

    void onRecyclerItemClick(View view, T item);
}
