package com.bpc.modulesdk.holders;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;

/**
 * Created by Smolyaninov on 03.03.2017.
 */

public class LocationHolder {

    private static LocationHolder instance = null;
    private LocationProvider locationProvider;

    private LocationHolder() {
        locationProvider = new LocationProvider();
    }

    public static LocationProvider getLocationProvider() {
        if (instance == null) {
            instance = new LocationHolder();
        }
        return instance.locationProvider;
    }

    public static void reset() {
        if (instance != null) {
            instance.locationProvider.disconnect();
            instance.locationProvider = null;
            instance = null;
        }
    }

    public static LocationEntry getLocation(){
        return getLocationProvider().getLocationEntry();
    }

}
