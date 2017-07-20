package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.AgentStatisticsDetailsEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.CommissionsInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.TransactionsInfoEntry;

import java.util.List;

/**
 * Created by Siarhei Mikevich on 6/1/17.
 */

public class AgentStatisticsResponse extends MainResponse {
    private CommissionsInfoEntry commissions;
    private TransactionsInfoEntry transactions;
    private List<AgentStatisticsDetailsEntry> details;

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

    public List<AgentStatisticsDetailsEntry> getDetails() {
        return details;
    }

    public void setDetails(List<AgentStatisticsDetailsEntry> details) {
        this.details = details;
    }

}
