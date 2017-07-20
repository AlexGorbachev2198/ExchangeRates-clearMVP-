package com.bpc.modulesdk.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPaymentByCashSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public CustomerPaymentByCashSupplyRequest() {
    }

    public CustomerPaymentByCashSupplyRequest(String transRef, DataResponse dataResponse) {
        this.transRef = transRef;
        this.dataResponse = dataResponse;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    public static class DataResponse {
        private String sourceAccount;

        public DataResponse() {
        }

        public DataResponse(String sourceAccount) {
            this.sourceAccount = sourceAccount;
        }

        public String getSourceAccount() {
            return sourceAccount;
        }

        public void setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
        }
    }
}
