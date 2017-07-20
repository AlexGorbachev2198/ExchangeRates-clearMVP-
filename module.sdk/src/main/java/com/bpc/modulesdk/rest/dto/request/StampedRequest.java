package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Masloed on 26.01.2017.
 */

public class StampedRequest {
     static final String INITIAL_SIGNATURE = "00000000000000000000";

    private String stamp = INITIAL_SIGNATURE;

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

}
