package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by Siarhei Mikevich on 6/2/17.
 */

public class TransactionTypesCount implements Serializable {

    private Integer payments;
    private Integer transfers;
    private Integer deposits;
    private Integer withdrawals;
    private Integer others;

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public Integer getTransfers() {
        return transfers;
    }

    public void setTransfers(Integer transfers) {
        this.transfers = transfers;
    }

    public Integer getDeposits() {
        return deposits;
    }

    public void setDeposits(Integer deposits) {
        this.deposits = deposits;
    }

    public Integer getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(Integer withdrawals) {
        this.withdrawals = withdrawals;
    }

    public Integer getOthers() {
        return others;
    }

    public void setOthers(Integer others) {
        this.others = others;
    }

}
