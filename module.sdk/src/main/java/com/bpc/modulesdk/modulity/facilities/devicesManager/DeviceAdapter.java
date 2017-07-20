package com.bpc.modulesdk.modulity.facilities.devicesManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bpc.modulesdk.ui.widgets.OnRecyclerItemClickListener;
import com.bpc.modulesdk.ui.widgets.OnRecyclerItemViewClickListener;

import java.util.List;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Samoylov on 17.01.2017.
 */

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.AdapterViewHolder> {

    private List<Device> devices;
    private OnRecyclerItemClickListener<Device> listener;

    public DeviceAdapter(List<Device> devices) {
        this.devices = devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
        notifyDataSetChanged();
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_device, parent, false));
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        Device device = devices.get(position);

        holder.textName.setText(device.getName());
        holder.itemView.setOnClickListener(new OnRecyclerItemViewClickListener<>(listener, device));
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener<Device> listener) {
        this.listener = listener;
    }

    public void add(Device device) {
        int position = devices.size();
        devices.add(device);
        notifyItemInserted(position);
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView textName;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.text_name);
        }
    }
}
