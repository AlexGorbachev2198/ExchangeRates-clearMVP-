package com.bpc.modulesdk.modulity.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;

import com.bpc.modulesdk.SdkSupportedFunctionality;
import com.bpc.modulesdk.modulity.facilities.UserRole;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 5/18/17.
 */

public interface BillPaymentUser extends Serializable {
    /**
     * Get user role for payment stuff
     * @return user role (customer, agent, etc.)
     */
    UserRole getUserRole();

    /**
     * Get string resource id of category title
     * @return string resource id
     */
    @StringRes
    int getSectionTitleId();

    /**
     * Return icon resource id for payment item
     * @return image id
     */
    int getImageResId();

    /**
     * Get title id
     * @return string resource id
     */
    @StringRes
    int getTitleResId();

    /**
     * Start payment activity
     * @param context context from view
     */
    void start(Context context, Bundle additionalData);

    boolean isEnableNow(SdkSupportedFunctionality supportedFunctionality);
}
