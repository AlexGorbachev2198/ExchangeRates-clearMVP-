package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 6/28/17.
 */

public class LoanRepaymentFromAccountAgentReceipt extends OperationAgentReceipt implements Receipt {
    private String customerCardMask;
    private String cardHolderName;
    private String sourceAccountNumber;
    private String loanAccountNumber;
    private String feeAmount;
    private String exchangeRate;
    private String totalAmount;

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

    public String getLoanAccountNumber() {
        return loanAccountNumber;
    }

    public void setLoanAccountNumber(String loanAccountNumber) {
        this.loanAccountNumber = loanAccountNumber;
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
        return helper.formatLoanRepaymentFromAccountReceipt(context, this);
    }
}
