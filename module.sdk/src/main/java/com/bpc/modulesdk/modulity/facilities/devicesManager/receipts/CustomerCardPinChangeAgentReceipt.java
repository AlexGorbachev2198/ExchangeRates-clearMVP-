package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by Aliaksandr Kotau on 6/15/17.
 */

public class CustomerCardPinChangeAgentReceipt extends OperationAgentReceipt implements Receipt {

    private String customerCardMask;

    public String getCustomerCardMask() {
        return customerCardMask;
    }

    public void setCustomerCardMask(String customerCardMask) {
        this.customerCardMask = customerCardMask;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatCustomerCardPinChangeReceipt(context, this);
    }
}
