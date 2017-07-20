package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.TransferRequiredData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/3/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentAcctToAcctTransferResponse extends MainResponse {
    private String transRef;
    private TransferRequiredData dataRequest;
    private OperationDetailsEntry operationDetails;

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

    public TransferRequiredData getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(TransferRequiredData dataRequest) {
        this.dataRequest = dataRequest;
    }
}
