package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.TransferRequiredData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/11/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashToAccountTransferResponse extends MainResponse {
    private String transRef;
    private TransferRequiredData dataRequest;

    public TransferRequiredData getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(TransferRequiredData dataRequest) {
        this.dataRequest = dataRequest;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }
}
