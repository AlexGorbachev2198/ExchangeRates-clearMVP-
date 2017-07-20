package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by Siarhei Mikevich on 5/2/17.
 */

public class CustomerNewCardCustomerReceipt extends OperationCustomerReceipt implements Receipt {

    private String customerCardMask;
    private String accountType;
    private String accountCurrency;

    public String getCustomerCardMask() {
        return customerCardMask;
    }

    public void setCustomerCardMask(String customerCardMask) {
        this.customerCardMask = customerCardMask;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatCustomerNewCardReceipt(context, this);
    }

}
