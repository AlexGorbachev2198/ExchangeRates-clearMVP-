package com.bpc.modulesdk.modulity.facilities.devicesManager;

/**
 * Created by Samoylov on 18.01.2017.
 * <p>
 * It is abstraction of supported device model, that can create concrete device examplar.
 */

public interface DeviceModel<T extends Device> {

    Class<T> getDeviceClass();

    T createDevice();
}
