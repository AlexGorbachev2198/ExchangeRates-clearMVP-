package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 4/13/17.
 */

public class CashToAccountAgentReceipt extends OperationAgentReceipt implements Receipt {
    private String destinationCardMask;
    private String destinationAccountNumber;

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
        return helper.formatCashToAccountReceipt(context, this);
    }
}
