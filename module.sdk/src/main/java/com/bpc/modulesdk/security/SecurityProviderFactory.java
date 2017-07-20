package com.bpc.modulesdk.security;

public class SecurityProviderFactory {

    public  static SecurityProvider getProvider() {
        return new SharedPreferencesSecurityProvider();
    }
}
