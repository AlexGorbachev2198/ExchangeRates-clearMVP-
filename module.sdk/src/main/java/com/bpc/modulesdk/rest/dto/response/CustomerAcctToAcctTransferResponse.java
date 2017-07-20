package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.TransferRequiredData;

/**
 * Created by Smolyaninov on 21.03.2017.
 */

public class CustomerAcctToAcctTransferResponse extends MainResponse {
    private String transRef;
    private TransferRequiredData dataRequest;

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
}
