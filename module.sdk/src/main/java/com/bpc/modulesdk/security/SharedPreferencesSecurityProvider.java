package com.bpc.modulesdk.security;

import android.support.annotation.Nullable;



public class SharedPreferencesSecurityProvider extends AbstractSecurityProvider {

    public SharedPreferencesSecurityProvider() {
        super();
    }

    @Override
    protected byte[] readBytes(String tag) {
        try {
            return Hex.decodeHex(SharedPreferencesHelper.getSecurityKey(tag).toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeBytes(String tag, @Nullable byte[] bytes) {
        SharedPreferencesHelper.setSecurityKey(tag, bytes != null ? String.valueOf(Hex.encodeHex(bytes)) : null);
    }
}