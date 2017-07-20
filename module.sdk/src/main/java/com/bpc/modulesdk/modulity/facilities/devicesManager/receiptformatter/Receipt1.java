package com.bpc.modulesdk.modulity.facilities.devicesManager.receiptformatter;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;

import java.util.List;

/**
 * Created by Masloed on 30.05.2017.
 */

public abstract class Receipt1 {

    private OperationDetailsEntry operationDetailsEntry;

    public Receipt1(OperationDetailsEntry operationDetailsEntry) {
        this.operationDetailsEntry = operationDetailsEntry;
    }

    protected OperationDetailsEntry getOperationDetailsEntry() {
        return operationDetailsEntry;
    }



    public abstract List<Line> getLines();
}
