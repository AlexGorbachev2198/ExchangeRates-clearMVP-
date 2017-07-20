package com.bpc.modulesdk.settings;

import android.app.Application;

import com.bpc.modulesdk.BaseApp;

/**
 * Created by Smolyaninov on 12.01.2017.
 */

public class ApplicationPropertiesProvider extends PropertiesProvider {

    public static final String PROPERTIES_FILE_NAME = "application.properties";

    private ApplicationPropertiesProvider(Application ctx) {
        super(ctx, PROPERTIES_FILE_NAME);
    }

    private static PropertiesProvider getPropertiesProvider() {
        return new ApplicationPropertiesProvider(BaseApp.getApp());
    }

    public static String getRuntimeSettings() {
        return getPropertiesProvider().getSetting(PropertiesKey.RUNTIME_SETTINGS.getKey());
    }

    public static String getHostSettings() {
        return getPropertiesProvider().getSetting(PropertiesKey.HOST.getKey());
    }

    public static String getServerAddress() {
        return "https://" + getHostSettings() + "/";
    }

    private enum PropertiesKey {

        HOST("network.host"),
        RUNTIME_SETTINGS("app.settings");

        String key;

        PropertiesKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}
