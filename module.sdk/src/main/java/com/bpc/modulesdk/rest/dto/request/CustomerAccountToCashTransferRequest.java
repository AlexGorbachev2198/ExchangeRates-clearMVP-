package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Siarhei Mikevich on 4/4/17.
 */

public class CustomerAccountToCashTransferRequest extends StampedRequest {

    private CustomerCardEntry sourceCard;
    private MoneyEntry operationAmount;
    private LocationEntry location;

    public CustomerAccountToCashTransferRequest(CustomerCardEntry sourceCard, MoneyEntry operationAmount, LocationEntry location) {
        this.sourceCard = sourceCard;
        this.operationAmount = operationAmount;
        this.location = location;
    }

    public CustomerCardEntry getSourceCard() {
        return sourceCard;
    }

    public void setSourceCard(CustomerCardEntry sourceCard) {
        this.sourceCard = sourceCard;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }

}
