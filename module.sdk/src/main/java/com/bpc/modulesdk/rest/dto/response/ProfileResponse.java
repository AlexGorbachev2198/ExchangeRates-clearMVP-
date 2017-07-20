package com.bpc.modulesdk.rest.dto.response;

/**
 * Created by Smolyaninov on 09.03.2017.
 */

public class ProfileResponse extends MainResponse {

    private int geoRestrictionEnabled;

    public int getGeoRestrictionEnabled() {
        return geoRestrictionEnabled;
    }

    public void setGeoRestrictionEnabled(int geoRestrictionEnabled) {
        this.geoRestrictionEnabled = geoRestrictionEnabled;
    }
}
