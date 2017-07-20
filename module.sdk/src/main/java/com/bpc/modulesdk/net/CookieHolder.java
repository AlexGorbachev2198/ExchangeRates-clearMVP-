package com.bpc.modulesdk.net;

import okhttp3.CookieJar;

public class CookieHolder {
    private static CookieHolder mInstance = null;

    public static CookieHolder get() {
        if (mInstance == null) {
            mInstance = new CookieHolder();
        }
        return mInstance;
    }

    //==============================================
    private CookieJar cookieJar;

    private CookieHolder() {
        cookieJar = new CustomCookieJar();
    }

    public CookieJar getCookieJar() {
        return this.cookieJar;
    }

    public void reset() {
        cookieJar = new CustomCookieJar();
    }
}