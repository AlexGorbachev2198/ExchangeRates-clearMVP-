package com.example.red_ragnar.testing;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.SdkConfig;

/**
 * Created by Red_Ragnar on 12.07.2017.
 */

public class BaseApp_heir extends BaseApp {
    @Override
    public boolean isDebug() {
        return false;
    }
    @Override
    public SdkConfig getSdkConfig() {
        return null;
    }
}
