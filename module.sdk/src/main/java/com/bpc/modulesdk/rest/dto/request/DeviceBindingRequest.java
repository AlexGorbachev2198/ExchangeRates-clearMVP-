package com.bpc.modulesdk.rest.dto.request;

import android.os.Build;
import android.util.DisplayMetrics;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;

import ru.bpc.mobilebanksdk.BuildConfig;

/**
 * Created by Masloed on 08.12.2016.
 */
public class DeviceBindingRequest {

    private String devId;
    private String devName;
    private String devKey;
    private String osType;
    private String osVersion;
    private String model;
    private String screen;
    private String appVersion;
    private LocationEntry location;
    private String vendor;

    public DeviceBindingRequest(String deviceName, String devId, String deviceKey) {
        this.devName = deviceName;
        this.devId = devId;
        this.devKey = deviceKey;

        DisplayMetrics metrics = BaseApp.getContext().getResources().getDisplayMetrics();
        String screen = String.valueOf(metrics.widthPixels) + "x" + String.valueOf(metrics.heightPixels);

        this.osType = "ANDROID";
        this.osVersion = Build.VERSION.RELEASE;
        this.vendor = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.appVersion = BuildConfig.VERSION_NAME;
        this.screen = screen;
    }


    public String getOsType() {
        return osType;
    }

    public String getOsVersion() {
        return osVersion;
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

    public String getAppVersion() {
        return appVersion;
    }

    public String getDevName() {
        return devName;
    }

    public String getDevId() {
        return devId;
    }

    public String getDevKey() {
        return devKey;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
