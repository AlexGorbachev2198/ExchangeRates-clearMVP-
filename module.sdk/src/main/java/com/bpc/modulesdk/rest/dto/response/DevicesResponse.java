package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.Device;

import java.util.List;

/**
 * Created by Samoylov on 31.01.2017.
 */

public class DevicesResponse extends MainResponse {

    private List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
