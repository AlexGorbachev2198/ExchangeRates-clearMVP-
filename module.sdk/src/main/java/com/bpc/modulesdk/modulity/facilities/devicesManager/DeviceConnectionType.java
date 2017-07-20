package com.bpc.modulesdk.modulity.facilities.devicesManager;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Samoylov on 18.01.2017.
 * <p>
 * Determine type of device connection
 */

public interface DeviceConnectionType {

    /**
     * start Device connection actity for result and return connected device id by {@link com.bpc.modulesdk.utils.ExtraKeys#DEVICE_ID}
     *
     * @param activity    which make {@link Activity#startActivityForResult(Intent, int)} and receive result
     * @param requestCode parameter of {@link Activity#startActivityForResult(Intent, int)}
     */
    void startConnection(Activity activity, int requestCode);


}
