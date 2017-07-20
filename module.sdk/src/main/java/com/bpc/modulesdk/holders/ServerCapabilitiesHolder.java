package com.bpc.modulesdk.holders;

import rx.Observable;

public class ServerCapabilitiesHolder {

    private static ServerCapabilitiesHolder instance = null;
    private ServerCapabilities serverCapabilities;

    private ServerCapabilitiesHolder() {
        serverCapabilities = new ServerCapabilities();
    }

    public static Observable<Object> getCapabilities() {
        if (instance == null) {
            instance = new ServerCapabilitiesHolder();
        }
        return instance.serverCapabilities.get();
    }

    public static void reset() {
        if (instance != null) {
            instance.serverCapabilities.stopPeriodicRequests();
            instance = null;
            getCapabilities();
        }
    }
}