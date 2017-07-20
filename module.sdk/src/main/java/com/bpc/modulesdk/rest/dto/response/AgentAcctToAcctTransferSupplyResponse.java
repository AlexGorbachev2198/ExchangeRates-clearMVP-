package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;

/**
 * Created by dzmitrystrupinski on 4/3/17.
 */

public class AgentAcctToAcctTransferSupplyResponse extends MainResponse {
    private String transRef;
    private OperationDetailsEntry operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;

    public OperationConfirmationRequestEntry getConfirmationRequest() {
        return confirmationRequest;
    }

    public void setConfirmationRequest(OperationConfirmationRequestEntry confirmationRequest) {
        this.confirmationRequest = confirmationRequest;
    }

    public OperationDetailsEntry getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(OperationDetailsEntry operationDetails) {
        this.operationDetails = operationDetails;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }
}
