package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;
import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentInfoEntry;

/**
 * Created by dzmitrystrupinski on 5/29/17.
 */

public class BillPaymentFromAccountAgentReceipt extends OperationAgentReceipt implements Receipt {
    private PaymentInfoEntry paymentInfo;
    private String customerCardMask;
    private String sourceAccountNumber;

    public PaymentInfoEntry getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoEntry paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getCustomerCardMask() {
        return customerCardMask;
    }

    public void setCustomerCardMask(String customerCardMask) {
        this.customerCardMask = customerCardMask;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatBillPaymentReceipt(context, this);
    }
}
