package com.bpc.modulesdk.modulity.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;

import com.bpc.modulesdk.modulity.facilities.UserRole;

/**
 * Created by Siarhei Mikevich on 6/26/17.
 */

public interface RepaymentUser extends ExtraControlled {

    /**
     * Get user role for management stuff
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
     * Start management activity
     * @param context context from view
     */
    void start(Context context, Bundle additionalData);

    /**
     * Return icon resource id for management item
     * @return image id
     */
    int getImageResId();

    /**
     * Get title id
     * @return string resource id
     */
    @StringRes
    int getTitleResId();

}
