package com.bpc.modulesdk.rest.dto.request;

import android.os.Build;
import android.util.DisplayMetrics;

import com.bpc.modulesdk.BaseApp;

/**
 * Created by Samoylov on 26.01.2017.
 */

public class DeviceInfoRequest extends StampedRequest {

    private String osType;
    private String osVersion;
    private String appVersion;
    private String vendor;
    private String model;
    private String screen;


    public DeviceInfoRequest() {
        DisplayMetrics metrics = BaseApp.getContext().getResources().getDisplayMetrics();

        osType = "ANDROID";
        osVersion = Build.VERSION.RELEASE;
        appVersion = BaseApp.getApp().getSdkConfig().getAppVersionName();
        vendor = Build.MANUFACTURER;
        model = Build.MODEL;
        screen = String.valueOf(metrics.widthPixels) + "x" + String.valueOf(metrics.heightPixels);
    }

    public String getOsType() {
        return osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }

    public String getScreen() {
        return screen;
    }
}
