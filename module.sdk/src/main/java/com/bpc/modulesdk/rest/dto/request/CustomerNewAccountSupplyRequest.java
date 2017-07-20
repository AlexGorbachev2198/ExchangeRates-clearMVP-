package com.bpc.modulesdk.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/20/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerNewAccountSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public CustomerNewAccountSupplyRequest(String transRef, DataResponse dataResponse) {
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
        private String accountType;
        private String accountCurrency;
        private String subagent;

        public DataResponse(String accountType, String accountCurrency, String subagent) {
            this.accountType = accountType;
            this.accountCurrency = accountCurrency;
            this.subagent = subagent;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getAccountCurrency() {
            return accountCurrency;
        }

        public void setAccountCurrency(String accountCurrency) {
            this.accountCurrency = accountCurrency;
        }

        public String getSubagent() {
            return subagent;
        }

        public void setSubagent(String subagent) {
            this.subagent = subagent;
        }
    }
}
