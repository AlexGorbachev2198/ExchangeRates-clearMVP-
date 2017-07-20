package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationProvidedDataEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 3/20/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashWithdrawalCompleteRequest extends StampedRequest {
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

    private String transRef;
    private OperationConfirmationProvidedDataEntry dataResponse;

    public CustomerCashWithdrawalCompleteRequest(String transRef, OperationConfirmationProvidedDataEntry dataResponse) {
        this.transRef = transRef;
        this.dataResponse = dataResponse;
    }
}
