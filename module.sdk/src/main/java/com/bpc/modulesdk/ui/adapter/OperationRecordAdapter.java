package com.bpc.modulesdk.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;
import com.bpc.modulesdk.ui.widgets.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import ru.bpc.mobilebanksdk.R;

public abstract class OperationRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_NORMAL = 0;
    private final int VIEW_TYPE_FOOTER = 1;

    List<OperationRecord> allItems = new ArrayList<>();
    OnRecyclerItemClickListener<OperationRecord> listener = null;
    private boolean isFooterVisible;
    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == VIEW_TYPE_FOOTER)
            return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer_progress, parent, false));
        else
            return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_operation, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            OperationRecord operation = allItems.get(position);
            fillViews((NormalViewHolder) holder, operation);
        }
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (allItems.get(position) == null)
            return VIEW_TYPE_FOOTER;
        else
            return VIEW_TYPE_NORMAL;
    }

    protected abstract void fillViews(NormalViewHolder holder, OperationRecord operation);

    public void addAll(List<OperationRecord> items) {
        if (isFooterVisible)
            allItems.remove(allItems.size() - 1);
        allItems.addAll(items);
        if (isFooterVisible)
            allItems.add(null);
        notifyDataSetChanged();
    }

    public void clean() {
        allItems = new ArrayList<>();
        if (isFooterVisible)
            allItems.add(null);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener<OperationRecord> listener) {
        this.listener = listener;
    }

    public void showProgressFooter(boolean isShow) {
        if (isFooterVisible == isShow)
            return;
        this.isFooterVisible = isShow;
        if (isFooterVisible) {
            allItems.add(null);
            notifyItemInserted(allItems.size());
        } else {
            allItems.remove(allItems.size() - 1);
            notifyItemRemoved(allItems.size());
        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView summ;
        TextView fee;
        TextView description;
        TextView time;
        TextView status;
        ImageView statusIcon;

        public NormalViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.item_icon);
            summ = (TextView) itemView.findViewById(R.id.operation_summ);
            fee = (TextView) itemView.findViewById(R.id.operation_fee);
            description = (TextView) itemView.findViewById(R.id.operation_description);
            time = (TextView) itemView.findViewById(R.id.operation_time);
            status = (TextView) itemView.findViewById(R.id.operation_status);
            statusIcon = (ImageView) itemView.findViewById(R.id.operation_icon);
        }
    }
}
