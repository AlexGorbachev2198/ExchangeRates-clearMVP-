package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Smolyaninov on 21.03.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAcctToAcctTransferRequest extends StampedRequest {

    private CustomerCardEntry sourceCard;
    private String targetCard;
    private MoneyEntry operationAmount;
    private String mode;
    private LocationEntry location;

    public CustomerAcctToAcctTransferRequest(CustomerCardEntry sourceCard, String targetCard, MoneyEntry operationAmount, String mode, LocationEntry location) {
        this.sourceCard = sourceCard;
        this.targetCard = targetCard;
        this.operationAmount = operationAmount;
        this.mode = mode;
        this.location = location;
    }

    public CustomerCardEntry getSourceCard() {
        return sourceCard;
    }

    public void setSourceCard(CustomerCardEntry sourceCard) {
        this.sourceCard = sourceCard;
    }

    public String getTargetCard() {
        return targetCard;
    }

    public void setTargetCard(String targetCard) {
        this.targetCard = targetCard;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
