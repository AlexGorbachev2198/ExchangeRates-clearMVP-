package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Smolyaninov on 10.04.2017.
 */

public class CustomerCashReceiveRequest extends StampedRequest {

    private String remittanceCode;
    private String receiverPhoneNumber;
    private MoneyEntry operationAmount;
    private LocationEntry location;

    public CustomerCashReceiveRequest(String remittanceCode, String receiverPhoneNumber, MoneyEntry operationAmount, LocationEntry location) {
        this.remittanceCode = remittanceCode;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.operationAmount = operationAmount;
        this.location = location;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }

    public String getRemittanceCode() {
        return remittanceCode;
    }

    public void setRemittanceCode(String remittanceCode) {
        this.remittanceCode = remittanceCode;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }
}
