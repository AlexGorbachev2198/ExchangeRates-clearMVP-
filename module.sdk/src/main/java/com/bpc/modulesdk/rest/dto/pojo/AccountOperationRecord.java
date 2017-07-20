package com.bpc.modulesdk.rest.dto.pojo;

import com.bpc.modulesdk.rest.dto.pojo.entries.ErrorDescriptionEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Smolyaninov on 14.06.2017.
 */

public class AccountOperationRecord extends OperationRecord {

    private MoneyEntry operationAmount;

    private MoneyEntry feeAmount;

    private ErrorDescriptionEntry errorDesc;
    private String settlementStatus;
    private String executionStatus;

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public ErrorDescriptionEntry getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(ErrorDescriptionEntry errorDesc) {
        this.errorDesc = errorDesc;
    }

    public MoneyEntry getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(MoneyEntry feeAmount) {
        this.feeAmount = feeAmount;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }
}
