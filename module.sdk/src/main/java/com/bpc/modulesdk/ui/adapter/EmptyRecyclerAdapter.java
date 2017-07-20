package com.bpc.modulesdk.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.bpc.mobilebanksdk.R;


/**
 * Created by Smolyaninov on 24.01.2017.
 */

public class EmptyRecyclerAdapter extends RecyclerView.Adapter {

    private String messageText;

    public EmptyRecyclerAdapter(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EmptyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_empty_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EmptyHolder emptyHolder = (EmptyHolder) holder;
        emptyHolder.textMessage.setText(messageText);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class EmptyHolder extends RecyclerView.ViewHolder {
        public TextView textMessage;

        public EmptyHolder(View view) {
            super(view);
            textMessage = (TextView) view.findViewById(R.id.text_view);
        }
    }
}
