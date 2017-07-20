package com.bpc.modulesdk.modulity.facilities.devicesManager;

import android.support.annotation.NonNull;

/**
 * Created by Samoylov on 13.01.2017.
 */

public interface Device {

    String getName();

    boolean isConnected();

    void disconnect();

    @NonNull
    String getId();

}
