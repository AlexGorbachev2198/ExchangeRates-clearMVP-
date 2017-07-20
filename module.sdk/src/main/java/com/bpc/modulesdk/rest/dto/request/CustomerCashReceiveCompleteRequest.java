package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;

/**
 * Created by Smolyaninov on 12.04.2017.
 */

public class CustomerCashReceiveCompleteRequest extends StampedRequest {

    private OperationConfirmationProvidedDataEntry dataResponse = new OperationConfirmationProvidedDataEntry();
    private String transRef;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public CustomerCashReceiveCompleteRequest(String transRef, String agentPass) {
        this.transRef = transRef;
        this.dataResponse.setAgentPass(agentPass);
    }

    public OperationConfirmationProvidedDataEntry getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(OperationConfirmationProvidedDataEntry dataResponse) {
        this.dataResponse = dataResponse;
    }
}
