package com.bpc.modulesdk.modulity.module;


import android.content.Context;

/**
 * Created by Samoylov on 26.01.2017.
 */
public interface SettingsListUser {

    int getTitleId();

    int getImageId();

    void onSettingClick(Context context);

    int getSettingsPositionOrder();

}
