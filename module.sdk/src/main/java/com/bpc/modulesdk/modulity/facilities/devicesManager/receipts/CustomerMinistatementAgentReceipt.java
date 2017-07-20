package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Smolyaninov on 09.06.2017.
 */

public class CustomerMinistatementAgentReceipt extends OperationAgentReceipt implements Receipt {

    private String sourceCardNumber;
    private String cardHolderName;
    private String sourceAccountNumber;
    private String sourceAccountCurrency;

    private MoneyEntry customerFee;

    public String getSourceCardNumber() {
        return sourceCardNumber;
    }

    public void setSourceCardNumber(String sourceCardNumber) {
        this.sourceCardNumber = sourceCardNumber;
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

    public MoneyEntry getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(MoneyEntry customerFee) {
        this.customerFee = customerFee;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatCustomerMinistatementAgentReceipt(context, this);
    }
}
