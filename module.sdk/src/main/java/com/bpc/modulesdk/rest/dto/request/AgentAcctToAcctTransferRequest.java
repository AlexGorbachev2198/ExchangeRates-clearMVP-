package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by dzmitrystrupinski on 4/3/17.
 */

public class AgentAcctToAcctTransferRequest extends StampedRequest {
    private String sourceAccount;
    private String targetAccount;
    private MoneyEntry operationAmount;
    private LocationEntry location;

    public AgentAcctToAcctTransferRequest(String sourceAccount, String targetAccount,MoneyEntry operationAmount, LocationEntry location) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.operationAmount = operationAmount;
        this.location = location;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }
}
