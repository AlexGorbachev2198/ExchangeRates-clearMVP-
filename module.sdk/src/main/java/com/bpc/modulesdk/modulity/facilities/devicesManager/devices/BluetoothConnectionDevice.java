package com.bpc.modulesdk.modulity.facilities.devicesManager.devices;

import android.bluetooth.BluetoothDevice;

import com.bpc.modulesdk.modulity.facilities.devicesManager.Device;

/**
 * Created by Samoylov on 16.01.2017.
 */

public interface BluetoothConnectionDevice extends Device {

    void connect(BluetoothDevice device, OnConnectedListener listener);

    interface OnConnectedListener {
        void onConnected();

        void onError();
    }
}
