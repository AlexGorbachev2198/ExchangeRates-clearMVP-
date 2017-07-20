package com.bpc.modulesdk.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Smolyaninov on 05.06.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerMinistatementSupplyRequest extends StampedRequest {

    private String transRef;
    private DataResponse response;

    public CustomerMinistatementSupplyRequest(String transRef, String sourceAccount) {
        this.transRef = transRef;
        this.response = new DataResponse();
        this.response.setSourceAccount(sourceAccount);
    }

    public static class DataResponse {
        private String sourceAccount;

        public String getSourceAccount() {
            return sourceAccount;
        }

        public void setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
        }
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }
}
