package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;
import com.bpc.modulesdk.modulity.facilities.devicesManager.devices.ReceiptsPrinter;

import java.io.Serializable;

/**
 * Created by Samoylov on 23.01.2017.
 * <p>
 * Receipt that can be printed on {@link ReceiptsPrinter}
 */

public interface Receipt extends Serializable {

    /**
     * Prepare receipt for printing
     *
     * @param helper implementation for special printer
     * @return formatted data
     */
    byte[] generate(Context context, ReceiptFormatter helper) throws Exception;

}
