package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by Siarhei Mikevich on 6/21/17.
 */

public class BalanceCustomerReceipt extends OperationCustomerReceipt implements Receipt {

    private String customerCardMask;
    private String cardHolderName;
    private String sourceAccountNumber;
    private String sourceAccountCurrency;
    private String customerFinalBalance;

    public String getCustomerCardMask() {
        return customerCardMask;
    }

    public void setCustomerCardMask(String customerCardMask) {
        this.customerCardMask = customerCardMask;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getSourceAccountCurrency() {
        return sourceAccountCurrency;
    }

    public void setSourceAccountCurrency(String sourceAccountCurrency) {
        this.sourceAccountCurrency = sourceAccountCurrency;
    }

    public String getCustomerFinalBalance() {
        return customerFinalBalance;
    }

    public void setCustomerFinalBalance(String customerFinalBalance) {
        this.customerFinalBalance = customerFinalBalance;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatBalanceCustomerReceipt(context, this);
    }

}
