package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 6/26/17.
 */

public class LoanRepaymentByCashCustomerReceipt extends OperationCustomerReceipt implements Receipt {
    private String accountNumber;
    private String feeAmount;
    private String exchangeRate;
    private String endingBalance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String getFeeAmount() {
        return feeAmount;
    }

    @Override
    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(String endingBalance) {
        this.endingBalance = endingBalance;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatLoanRepaymentByCashReceipt(context, this);
    }
}
