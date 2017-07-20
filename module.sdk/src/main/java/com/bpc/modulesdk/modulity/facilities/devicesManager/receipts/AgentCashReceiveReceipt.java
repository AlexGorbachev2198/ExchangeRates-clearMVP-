package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Smolyaninov on 09.06.2017.
 */

public class AgentCashReceiveReceipt extends OperationAgentReceipt implements Receipt {

    private String receiverPhoneNumber;
    private MoneyEntry customerFee;
    private MoneyEntry commissionAmount;
    //private MoneyEntry beginningBalance;
    //private MoneyEntry endingBalance;

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

    public MoneyEntry getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(MoneyEntry commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    /*public MoneyEntry getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(MoneyEntry beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public MoneyEntry getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(MoneyEntry endingBalance) {
        this.endingBalance = endingBalance;
    }*/

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatAgentCashReceiveReceipt(context, this);
    }
}
