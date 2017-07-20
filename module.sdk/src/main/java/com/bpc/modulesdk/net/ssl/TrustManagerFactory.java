package com.bpc.modulesdk.net.ssl;

import javax.net.ssl.TrustManager;


public class TrustManagerFactory {


    public static TrustManager getTrustManager() {
        //ACLTrustManager trustManager = new ACLTrustManager(KeyStoreProvider.getApplicationKeystore(BaseApp.getApp().getApplicationContext()));
        ACLTrustManager trustManager = new ACLTrustManager();
        String whiteTimeoutString = "600000";
        String blackTimeoutString = "1500";
        try {
            trustManager.setWhitelistTimeoutMs(Long.parseLong(whiteTimeoutString));
        } catch (Exception ignored) {
        }
        try {
            trustManager.setBlacklistTimeoutMs(Long.parseLong(blackTimeoutString));
        } catch (Exception ignored) {
        }
        return trustManager;
    }
}