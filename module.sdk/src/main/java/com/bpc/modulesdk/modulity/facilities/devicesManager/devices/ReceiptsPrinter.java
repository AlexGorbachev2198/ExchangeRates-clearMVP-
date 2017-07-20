package com.bpc.modulesdk.modulity.facilities.devicesManager.devices;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bpc.modulesdk.modulity.facilities.devicesManager.Device;
import com.bpc.modulesdk.modulity.facilities.devicesManager.receipts.Receipt;

/**
 * Created by Samoylov on 12.01.2017.
 * <p>
 * Interface of device that can print receipt
 */

public interface ReceiptsPrinter extends Device {

    void printReceipt(Context context, @NonNull OnCompleteListener completeListener,  @NonNull OnErrorListener errorListener, Receipt receipt);

    interface OnCompleteListener {
        void onComplete();
    }

    interface OnErrorListener {
        void onError();
    }

}
