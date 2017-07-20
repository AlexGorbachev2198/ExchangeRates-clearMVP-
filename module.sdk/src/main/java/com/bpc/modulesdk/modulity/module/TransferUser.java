package com.bpc.modulesdk.modulity.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;

import com.bpc.modulesdk.modulity.facilities.UserRole;

public interface TransferUser extends ExtraControlled {
    // returns user role related to this transfer
    UserRole getUserRole();

    @StringRes
    int getSectionTitleId();

    /**
     * Start a transfer
     *
     * @param context context from view
     */
    void start(Context context, Bundle additionalData);

    /**
     * Return view that have {@link android.view.View.OnClickListener}, that start the transfer
     *
     * @return transfers list item view
     */
    View getNewTransferView(Context context, Bundle additionalData);

    /**
     * Return identification of this transfer. With that tag you can find special transfer in list.
     *
     * @return tag
     */
    String getTag();

    int getImageResId();

    int getTitleResId();

    int getNameResId();

    int getDescriptionResId();

}
