package com.bpc.modulesdk.ui.widgets.currencyrateswidget.nameswidget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 03.02.2017.
 */

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder> {

    private List<String> names;

    public NamesAdapter(List<String> list) {
        this.names = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_currency_name_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = position % names.size();
        String ri = names.get(position);
        holder.currencyName.setText(ri);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView currencyName;

        public ViewHolder(View itemView) {
            super(itemView);
            currencyName = (TextView) itemView.findViewById(R.id.currency_name_text_view);
        }
    }

    public String getItem(int position) {
        return names.get(position);
    }

    public void setItem(int position, String item) {
        names.set(position, item);
    }
}
