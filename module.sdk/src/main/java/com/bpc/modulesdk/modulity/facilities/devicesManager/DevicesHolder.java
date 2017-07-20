package com.bpc.modulesdk.modulity.facilities.devicesManager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bpc.modulesdk.activity.SelectConnectedDeviceActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Samoylov on 13.01.2017.
 * <p>
 * Class that store and manage connected devices.
 */

public class DevicesHolder {

    private static List<Device> devices = new ArrayList<>();
    private static Device activeDevice;

    public static Device getActiveDevice() {
        return activeDevice != null && activeDevice.isConnected() ? activeDevice : null;
    }

    public static void setActiveDevice(String devId) {
        Device d = getConnectedDevice(devId);
        if (d != null) DevicesHolder.activeDevice = d;
    }

    public static <T extends Device> boolean isActiveDevice(@NonNull Class<T> clazz) {
        return activeDevice != null && activeDevice.isConnected() && clazz.isInstance(activeDevice);
    }

    public static void addConnectedDevice(@NonNull Device device) {
        devices.add(device);
    }

    /**
     * Collect all connected devices
     *
     * @return devices whose connection is active.
     */
    @NonNull
    public static List<Device> getConnectedDevices() {
        return devices;
    }

    /**
     * Return connected devices that implement {@code T}
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @NonNull
    public static <T extends Device> List<T> getConnectedDevices(Class<T> clazz) {
        if (clazz == null)
            return new ArrayList(devices);
        List<T> result = new LinkedList<>();
        for (Device device : devices)
            if (device.isConnected() && clazz.isInstance(device))
                result.add((T) device);
        return result;
    }

    /**
     * Find examplar of {@link Device}
     *
     * @param id value that returned {@link Device#getId()}
     * @return connected {@link Device}
     */
    @Nullable
    public static Device getConnectedDevice(String id) {
        if (id == null)
            return null;
        for (Device device : devices)
            if (id.equals(device.getId()))
                return device;
        return null;
    }

    /**
     * Create intent for starting Activity fro select {@link Device}. As result will be returned
     * device id by {@link com.bpc.modulesdk.utils.ExtraKeys#DEVICE_ID} key
     *
     * @param context         any
     * @param deviceInterface which interface device have to implement
     * @param title           title of select device activity
     * @param <T>
     * @return intent for startingActivityForResult
     */
    public static <T extends Device> Intent createSelectDeviceIntent(Context context, Class<T> deviceInterface, String title) {
        return SelectConnectedDeviceActivity.getIntent(context, deviceInterface, title);
    }


}
