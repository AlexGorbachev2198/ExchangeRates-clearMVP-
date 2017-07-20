package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;

/**
 * Created by Siarhei Mikevich on 4/4/17.
 */

public class CustomerAccountToCashTransferCompleteRequest extends StampedRequest {

    private String transRef;
    private OperationConfirmationProvidedDataEntry dataResponse;

    public CustomerAccountToCashTransferCompleteRequest(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        this.transRef = transRef;
        this.dataResponse = dataResponse;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public OperationConfirmationProvidedDataEntry getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(OperationConfirmationProvidedDataEntry dataResponse) {
        this.dataResponse = dataResponse;
    }
}
