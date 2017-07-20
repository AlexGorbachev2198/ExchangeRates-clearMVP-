package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Smolyaninov on 09.06.2017.
 */

public class CustomerCashReceiveReceipt extends OperationCustomerReceipt implements Receipt {

    private String receiverPhoneNumber;
    private MoneyEntry customerFee;

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public MoneyEntry getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(MoneyEntry customerFee) {
        this.customerFee = customerFee;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatCustomerCashReceiveReceipt(context, this);
    }
}
