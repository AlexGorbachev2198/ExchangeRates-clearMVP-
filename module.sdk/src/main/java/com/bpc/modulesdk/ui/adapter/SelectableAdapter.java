package com.bpc.modulesdk.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Samoylov on 31.01.2017.
 */

public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private ActionModeCallback actionMode;

    public SelectableAdapter(ActionModeCallback actionMode) {
        this.actionMode = actionMode;
        actionMode.bind(this);
    }

    private final HashSet<Integer> selectedItems = new HashSet<>();

    public abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(int position, VH holder);

    protected abstract void onRecyclerItemClick(View view, int position);

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent, viewType);
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(position, holder);
        boolean isSelected = isItemSelected(position);
        holder.itemView.setSelected(isSelected);
        holder.itemView.setOnLongClickListener(v -> onItemLongClick(holder, position));
        holder.itemView.setOnClickListener(v -> onItemClick(v, holder, position));
    }

    private boolean onItemLongClick(VH holder, int position) {
        if (isItemSelectable(position)) {
            select(holder, position);
            return true;
        }
        return false;
    }

    private void onItemClick(View view, VH holder, int position) {
        if (isItemSelectable(position) && actionMode.isActionModeStarted())
            select(holder, position);
        else onRecyclerItemClick(view, position);

    }

    private void select(VH holder, int position) {
        boolean isSelected = isItemSelected(position);
        holder.itemView.setSelected(!isSelected);
        if (isSelected)
            selectedItems.remove(position);
        else
            selectedItems.add(position);
        if (getSelectedCount() == 1)
            actionMode.start();
    }

    public boolean isItemSelected(int position) {
        return selectedItems.contains(position);
    }

    public int getSelectedCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedPositions() {
        return new ArrayList<>(selectedItems);
    }

    public boolean isItemSelectable(int position) {
        return true; //By default all items selectable
    }

    void clearSelection() {
        if (selectedItems.isEmpty())
            return;
        List<Integer> selectedItems = new ArrayList<>(this.selectedItems);
        this.selectedItems.clear();
        Collections.sort(selectedItems);
        for (Integer item : selectedItems)
            notifyItemChanged(item);
    }

}
