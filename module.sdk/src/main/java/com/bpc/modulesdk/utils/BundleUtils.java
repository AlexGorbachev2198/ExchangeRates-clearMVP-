package com.bpc.modulesdk.utils;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dzmitrystrupinski on 5/26/17.
 */

public class BundleUtils {
    public static int getIntExtrasValue(Intent intent, String key, int defaultValue) {
        Bundle extras = intent.getExtras();
        return extras != null ? extras.getInt(key, defaultValue) : defaultValue;
    }
}
