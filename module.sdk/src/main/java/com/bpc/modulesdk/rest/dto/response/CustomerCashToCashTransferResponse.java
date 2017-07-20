package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.TransferRequiredData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/17/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashToCashTransferResponse extends MainResponse {
    private String transRef;
    private TransferRequiredData dataRequest;
    private OperationDetailsEntry operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public TransferRequiredData getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(TransferRequiredData dataRequest) {
        this.dataRequest = dataRequest;
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
