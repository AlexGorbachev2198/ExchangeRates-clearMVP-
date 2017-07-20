package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by Smolyaninov on 13.06.2017.
 */

public class AgentCustomerAccToAccReceipt extends OperationAgentReceipt implements Receipt {

    private String customerCardMask;
    private String sourceAccountNumber;

    private String destinationCardMask;
    private String destinationAccountNumber;

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

    public String getDestinationCardMask() {
        return destinationCardMask;
    }

    public void setDestinationCardMask(String destinationCardMask) {
        this.destinationCardMask = destinationCardMask;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }


    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatAgentCustomerAccToAccReceipt(context, this);
    }
}
