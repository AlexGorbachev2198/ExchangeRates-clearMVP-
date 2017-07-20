package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 4/14/17.
 */

public class CashDepositCustomerReceipt extends OperationCustomerReceipt implements Receipt {

    private String customerCardMask;

    public String getCustomerCardMask() {
        return customerCardMask;
    }

    public void setCustomerCardMask(String customerCardMask) {
        this.customerCardMask = customerCardMask;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatCashDepositReceipt(context, this);
    }
}
