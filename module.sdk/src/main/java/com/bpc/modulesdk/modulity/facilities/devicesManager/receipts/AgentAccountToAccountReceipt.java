package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;

/**
 * Created by dzmitrystrupinski on 4/13/17.
 */

public class AgentAccountToAccountReceipt extends OperationAgentReceipt implements Receipt {
    private String sourceAccountNumber;
    private String targetAccountNumber;

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatAgentAccountToAccountReceipt(context, this);
    }
}
