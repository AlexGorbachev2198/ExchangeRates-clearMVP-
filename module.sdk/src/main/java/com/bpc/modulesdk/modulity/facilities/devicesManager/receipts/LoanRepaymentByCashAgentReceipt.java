package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 6/26/17.
 */

public class LoanRepaymentByCashAgentReceipt extends OperationAgentReceipt implements Receipt {
    private String accountNumber;
    private String feeAmount;
    private String exchangeRate;
    private String totalAmount;

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

    @Override
    public String getTotalAmount() {
        return totalAmount;
    }

    @Override
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatLoanRepaymentByCashReceipt(context, this);
    }
}
