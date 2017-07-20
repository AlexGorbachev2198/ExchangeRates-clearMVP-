package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by Siarhei Mikevich on 6/1/17.
 */

public class AgentStatisticsDetailsEntry implements Serializable {

    private String date;
    private CommissionsInfoEntry commissions;
    private TransactionsInfoEntry transactions;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CommissionsInfoEntry getCommissions() {
        return commissions;
    }

    public void setCommissions(CommissionsInfoEntry commissions) {
        this.commissions = commissions;
    }

    public TransactionsInfoEntry getTransactions() {
        return transactions;
    }

    public void setTransactions(TransactionsInfoEntry transactions) {
        this.transactions = transactions;
    }

}
