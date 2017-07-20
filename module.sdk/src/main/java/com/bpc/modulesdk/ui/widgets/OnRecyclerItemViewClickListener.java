package com.bpc.modulesdk.ui.widgets;

import android.view.View;

/**
 * Created by Samoylov on 13.01.2017.
 * <p>
 * Listener for setting OnClickListener to RecyclerView item view.
 */
public class OnRecyclerItemViewClickListener<T> implements View.OnClickListener {
    private OnRecyclerItemClickListener<T> listener;
    private T item;

    public OnRecyclerItemViewClickListener(OnRecyclerItemClickListener<T> listener, T item) {
        this.listener = listener;
        this.item = item;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onRecyclerItemClick(v, item);
    }
}
