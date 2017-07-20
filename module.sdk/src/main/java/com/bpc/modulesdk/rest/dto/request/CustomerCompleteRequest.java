package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Smolyaninov on 05.06.2017.
 */

@JsonIgnoreProperties (ignoreUnknown = true)
public class CustomerCompleteRequest extends StampedRequest {

    private String transRef;
    private OperationConfirmationProvidedDataEntry dataResponse;

    public CustomerCompleteRequest(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
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
