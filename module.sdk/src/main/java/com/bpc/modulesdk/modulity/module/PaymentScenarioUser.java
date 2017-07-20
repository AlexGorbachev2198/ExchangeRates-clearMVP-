package com.bpc.modulesdk.modulity.module;

import android.app.Activity;

/**
 * Created by Masloed on 12.04.2016.
 */
@Deprecated
public interface PaymentScenarioUser {
    String EXTRA_PAYMENT_PID = "payment_pid";

    String getScenarioType();

    Class<? extends Activity> getActivityClass();
}
