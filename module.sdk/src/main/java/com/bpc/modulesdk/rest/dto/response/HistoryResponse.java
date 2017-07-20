package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;

import java.util.List;


public abstract class HistoryResponse extends MainResponse {
    private int page;

    public abstract List<? extends OperationRecord> getHistory();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}