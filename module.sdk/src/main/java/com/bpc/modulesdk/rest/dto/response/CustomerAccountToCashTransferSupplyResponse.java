package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Siarhei Mikevich on 4/4/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAccountToCashTransferSupplyResponse extends MainResponse {
    private String transRef;
    private OperationDetailsEntry operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public OperationDetailsEntry getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(OperationDetailsEntry operationDetails) {
        this.operationDetails = operationDetails;
    }

    public OperationConfirmationRequestEntry getConfirmationRequest() {
        return confirmationRequest;
    }

    public void setConfirmationRequest(OperationConfirmationRequestEntry confirmationRequest) {
        this.confirmationRequest = confirmationRequest;
    }

}
