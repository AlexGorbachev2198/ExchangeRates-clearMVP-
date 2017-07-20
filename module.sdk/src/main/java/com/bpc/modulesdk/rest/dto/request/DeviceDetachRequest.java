package com.bpc.modulesdk.rest.dto.request;

import java.util.List;

/**
 * Created by Samoylov on 01.02.2017.
 */

public class DeviceDetachRequest extends StampedRequest {

    public List<String> devids;

    public DeviceDetachRequest(List<String> devids) {
        this.devids = devids;
    }
}
