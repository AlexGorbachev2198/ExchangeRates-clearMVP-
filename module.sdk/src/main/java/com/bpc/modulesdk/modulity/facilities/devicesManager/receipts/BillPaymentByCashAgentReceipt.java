package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;
import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentInfoEntry;

/**
 * Created by dzmitrystrupinski on 5/23/17.
 */

public class BillPaymentByCashAgentReceipt extends OperationAgentReceipt implements Receipt {
    private PaymentInfoEntry paymentInfo;
    private String customerPhoneNumber;

    public PaymentInfoEntry getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoEntry paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatBillPaymentReceipt(context, this);
    }
}
