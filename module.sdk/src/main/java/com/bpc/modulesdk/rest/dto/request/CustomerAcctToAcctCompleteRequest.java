package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;

/**
 * Created by Smolyaninov on 30.03.2017.
 */

public class CustomerAcctToAcctCompleteRequest extends StampedRequest {

    private String transRef;
    private OperationConfirmationProvidedDataEntry dataResponse;

    public CustomerAcctToAcctCompleteRequest() {
    }

    public CustomerAcctToAcctCompleteRequest(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
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
