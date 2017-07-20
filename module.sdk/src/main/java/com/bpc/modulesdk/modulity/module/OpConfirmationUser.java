package com.bpc.modulesdk.modulity.module;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.OpConfirmatorDescriptor;

import rx.Observable;

public interface OpConfirmationUser {
    Observable<OpConfirmatorDescriptor> getOpConfirmatorDescriptorObservable(Context context);
}