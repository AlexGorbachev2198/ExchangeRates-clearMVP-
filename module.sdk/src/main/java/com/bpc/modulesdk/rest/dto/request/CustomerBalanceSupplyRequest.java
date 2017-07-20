package com.bpc.modulesdk.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Masloed on 06.02.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerBalanceSupplyRequest extends StampedRequest {

    private String transRef;
    private DataResponse dataResponse;

    public CustomerBalanceSupplyRequest() {

    }

    public CustomerBalanceSupplyRequest(String transRef, DataResponse dataResponse) {
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

    public void setProvidedData(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    public static class DataResponse {

        private String cardAccount;

        public String getCardAccount() {
            return cardAccount;
        }

        public void setCardAccount(String cardAccount) {
            this.cardAccount = cardAccount;
        }

    }

}
