package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.CardOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;

import java.util.List;

/**
 * Created by Smolyaninov on 14.06.2017.
 */

public class CardHistoryResponse extends HistoryResponse {

    private List<CardOperationRecord> operationRecords;

    @Override
    public List<? extends OperationRecord> getHistory() {
        return operationRecords;
    }

    public void setHistory(List<CardOperationRecord> history) {
        this.operationRecords = history;
    }
}
