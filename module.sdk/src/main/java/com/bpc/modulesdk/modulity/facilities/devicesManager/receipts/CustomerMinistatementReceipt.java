package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.devicesManager.ReceiptFormatter;
import com.bpc.modulesdk.rest.dto.pojo.entries.MinistatementRecord;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

import java.util.List;

/**
 * Created by Smolyaninov on 08.06.2017.
 */

public class CustomerMinistatementReceipt extends OperationCustomerReceipt implements Receipt {


    private String sourceCardNumber;
    private String cardHolderName;
    private String sourceAccountNumber;
    private String sourceAccountCurrency;

    private List<MinistatementRecord> ministatements;

    private MoneyEntry customerFee;
    private MoneyEntry totalBalance;

    private MoneyEntry availableBalance;




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

    public List<MinistatementRecord> getMinistatements() {
        return ministatements;
    }

    public void setMinistatements(List<MinistatementRecord> ministatements) {
        this.ministatements = ministatements;
    }

    public MoneyEntry getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(MoneyEntry customerFee) {
        this.customerFee = customerFee;
    }

    public MoneyEntry getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(MoneyEntry totalBalance) {
        this.totalBalance = totalBalance;
    }

    public MoneyEntry getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(MoneyEntry availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public byte[] generate(Context context, ReceiptFormatter helper) throws Exception {
        return helper.formatCustomerMinisatementReceipt(context, this);
    }
}
