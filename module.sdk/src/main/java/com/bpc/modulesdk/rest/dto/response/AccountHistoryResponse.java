package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.AccountOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smolyaninov on 14.06.2017.
 */

public class AccountHistoryResponse extends HistoryResponse {

    private List<AccountOperationRecord> history = new ArrayList<>();

    @Override
    public List<? extends OperationRecord> getHistory() {
        return history;
    }


    public void setHistory(List<AccountOperationRecord> history) {
        this.history = history;
    }

}
