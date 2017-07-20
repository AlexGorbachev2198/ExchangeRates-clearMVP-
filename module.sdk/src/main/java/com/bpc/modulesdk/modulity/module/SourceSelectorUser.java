package com.bpc.modulesdk.modulity.module;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.bpc.modulesdk.modulity.facilities.paymentsource.PaymentSourceItem;
import com.bpc.modulesdk.rest.dto.pojo.PaymentSourceType;
import com.bpc.modulesdk.rest.dto.pojo.StorageOptionsEntry;

/**
 * Created by Samoylov on 20.01.2017.
 */

public interface SourceSelectorUser {

    PaymentSourceType getPaymentSourceType();

    @Nullable
    PaymentSourceItem getPaymentSourceItem(String sourceID);

    Fragment getSelectionListFragment(StorageOptionsEntry storageOptionsEntry, boolean isNotActiveAllowed);

    int getFragmentTitleResID();

    Fragment getHistorySelectionListFragment();

}