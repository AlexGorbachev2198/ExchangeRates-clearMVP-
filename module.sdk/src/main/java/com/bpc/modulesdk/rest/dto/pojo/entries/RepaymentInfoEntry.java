package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by Siarhei Mikevich on 6/26/17.
 */

public class RepaymentInfoEntry implements Serializable {

    private String accountNumber;
    private String accountHolder;
    private String accountCurrency;
    private MoneyEntry operationAmount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

}
