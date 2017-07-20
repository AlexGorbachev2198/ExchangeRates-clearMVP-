package com.bpc.modulesdk;

import com.bpc.modulesdk.modulity.facilities.mainmenu.MainMenu;

import java.util.List;

public interface SdkConfig {

    Class getFirstActivity();

    Class getMainActivity();

    Class getSplashActivity();

    List<Object> getDefaultUsers();

    String[] getModuleNames();

    MainMenu getMainMenu();

    String getAppVersionName();

    SdkSupportedFunctionality getSupportedFunctionality();
}