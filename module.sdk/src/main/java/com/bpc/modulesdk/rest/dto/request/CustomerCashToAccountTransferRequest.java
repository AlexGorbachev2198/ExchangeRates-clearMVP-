package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/11/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashToAccountTransferRequest extends StampedRequest {
    private String targetCard;
    private MoneyEntry operationAmount;
    private LocationEntry location;

    public CustomerCashToAccountTransferRequest(String targetCard, MoneyEntry operationAmount, LocationEntry location) {
        this.targetCard = targetCard;
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

    public String getTargetCard() {
        return targetCard;
    }

    public void setTargetCard(String targetCard) {
        this.targetCard = targetCard;
    }
}
