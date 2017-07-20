package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 4/19/17.
 */

public class CashToCashCustomerReceipt extends OperationCustomerReceipt implements Receipt {
    private String senderPhone;
    private String receiverPhone;
    private String withdrawalCode;

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getWithdrawalCode() {
        return withdrawalCode;
    }

    public void setWithdrawalCode(String withdrawalCode) {
        this.withdrawalCode = withdrawalCode;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatCashToCashReceipt(context, this);
    }
}
