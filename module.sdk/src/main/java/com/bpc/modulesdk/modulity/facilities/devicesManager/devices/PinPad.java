package com.bpc.modulesdk.modulity.facilities.devicesManager.devices;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bpc.modulesdk.modulity.facilities.devicesManager.Device;

/**
 * Created by Samoylov on 11.01.2017.
 * <p>
 * Base interface of any pos terminal
 */

public interface PinPad extends Device {


    void requestCardData(@NonNull Context context);

    void cancelRequestCardData();

    void requestPin(@NonNull Context context);

    void cancelRequestPin();

}
