package com.bpc.modulesdk.settings;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.bpc.modulesdk.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {

    private final String DEFAULT_SPLIT = "\\|";

    private Properties properties;

    public PropertiesProvider(Application ctx, String propertiesFileName) {
        readProperties(ctx, propertiesFileName);
    }

    public String getSetting(String settingName) {
        return properties.getProperty(settingName);
    }

    public Boolean getBoolean(String settingName, Boolean defaultValue) {
        String value = getSetting(settingName);
        return StringUtils.isNotBlank(value) ? Boolean.parseBoolean(value) : defaultValue;
    }

    public String[] getSettings(String settingName) {
        if (properties != null) {
            String property = properties.getProperty(settingName);
            if (property == null || property.isEmpty())
                return null;
            return property.split(DEFAULT_SPLIT);
        }
        return null;
    }

    protected void readProperties(Context ctx, String propertiesFileName) {
        AssetManager assetManager = ctx.getAssets();
        try {
            InputStream inputStream = assetManager.open(propertiesFileName);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            Log.e("PropertiesProvider", "Can't load app properties", e);
        } finally {
            //assetManager.close();
        }
    }
}