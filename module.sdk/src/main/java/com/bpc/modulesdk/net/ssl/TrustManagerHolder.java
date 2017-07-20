package com.bpc.modulesdk.net.ssl;

import com.bpc.modulesdk.net.ssl.TrustManagerFactory;

import javax.net.ssl.TrustManager;

public class TrustManagerHolder {
    private static TrustManagerHolder mInstance = null;


    public static TrustManagerHolder get() {
        if (mInstance == null) {
            mInstance = new TrustManagerHolder();
        }
        return mInstance;
    }

    //==============================================
    private TrustManager trustManager;


    private TrustManagerHolder() {
        trustManager = TrustManagerFactory.getTrustManager();
    }

    public TrustManager getTrustManager() {
        return this.trustManager;
    }


}