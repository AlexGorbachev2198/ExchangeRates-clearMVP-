package com.bpc.modulesdk.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.modulity.facilities.sessionFacility.SessionFacility;

/**
 * Created by Samoylov on 30.01.2017.
 */

public class LanguageChangeBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "LanguageChangeReceiver";
    private static LanguageChangeBroadcastReceiver instance;

    private LanguageChangeBroadcastReceiver() {
    }

    public static void register(Context context) {
        if (instance != null)
            return;
        instance = new LanguageChangeBroadcastReceiver();
        context.registerReceiver(instance, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SessionFacility.closeSessionDeferred();
    }


    public static void doOnSessionOpen() {
        LanguageChangeBroadcastReceiver.register(BaseApp.getContext());
    }
}
