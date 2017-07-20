package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 5/11/17.
 */

public class NewCustomerReceipt extends OperationCustomerReceipt implements Receipt {
    private String newCustomerCardMask;
    private String accountType;
    private String accountCurrency;

    public String getNewCustomerCardMask() {
        return newCustomerCardMask;
    }

    public void setNewCustomerCardMask(String newCustomerCardMask) {
        this.newCustomerCardMask = newCustomerCardMask;
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
        return helper.formatNewCustomerReceipt(context, this);
    }
}
