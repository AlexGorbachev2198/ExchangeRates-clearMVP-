package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Siarhei Mikevich on 4/26/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerNewCardSupplyRequest extends StampedRequest {

    private String transRef;
    private DataResponse dataResponse;

    public CustomerNewCardSupplyRequest(String transRef, DataResponse dataResponse) {
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

        private CustomerCardEntry newCard;
        private String accountType;
        private String accountCurrency;
        private String subagent;

        public DataResponse(CustomerCardEntry newCard) {
            this.newCard = newCard;
        }

        public DataResponse(String accountType, String accountCurrency, String subagent) {
            this.accountType = accountType;
            this.accountCurrency = accountCurrency;
            this.subagent = subagent;
        }

        public CustomerCardEntry getNewCard() {
            return newCard;
        }

        public void setNewCard(CustomerCardEntry newCard) {
            this.newCard = newCard;
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
