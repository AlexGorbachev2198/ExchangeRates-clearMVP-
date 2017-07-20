package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationInfoEntry;

import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/25/17.
 */

public class CustomerOperationsHistoryResponse extends MainResponse {
    private List<OperationInfoEntry> operations;

    public List<OperationInfoEntry> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationInfoEntry> operations) {
        this.operations = operations;
    }
}
