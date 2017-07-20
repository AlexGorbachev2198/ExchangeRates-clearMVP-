package com.bpc.modulesdk;

import android.app.Application;

import com.bpc.modulesdk.settings.PropertiesProvider;

public class ModulePropertiesProvider extends PropertiesProvider {
    public ModulePropertiesProvider(Application ctx) {
        super(ctx, "module.properties");
    }
}