package com.bpc.modulesdk;

import android.app.Application;
import android.content.Context;

public abstract class BaseApp extends Application {
    public static final String FAKE_SERVICE = "fake";
    public static final String APP_SETTINGS_RUNTIME = "runtime";
    public static boolean isDebug;
    private static BaseApp app;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static BaseApp getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        app = this;
        isDebug = isDebug();
    }

    /**
     * Необходимо переопределить в application модуле и вернуть {@link BuildConfig#DEBUG}
     *
     * @return - true, если приложение собрано в Debug варианте.
     */
    public abstract boolean isDebug();

    public abstract SdkConfig getSdkConfig();


}
