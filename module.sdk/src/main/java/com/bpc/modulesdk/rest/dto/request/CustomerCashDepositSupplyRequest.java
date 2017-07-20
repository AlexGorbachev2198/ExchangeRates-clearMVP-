package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Siarhei Mikevich on 3/21/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashDepositSupplyRequest extends StampedRequest {

    private String transRef;
    private ProvidedData dataResponse;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public ProvidedData getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(ProvidedData dataResponse) {
        this.dataResponse = dataResponse;
    }

    public CustomerCashDepositSupplyRequest(String transRef, ProvidedData dataResponse) {
        this.transRef = transRef;
        this.dataResponse = dataResponse;
    }

    public static class ProvidedData {

        private String cardAccount;
        private String agentAccount;
        private MoneyEntry operationAmount;

        public String getCardAccount() {
            return cardAccount;
        }

        public void setCardAccount(String cardAccount) {
            this.cardAccount = cardAccount;
        }

        public String getAgentAccount() {
            return agentAccount;
        }

        public void setAgentAccount(String agentAccount) {
            this.agentAccount = agentAccount;
        }

        public MoneyEntry getOperationAmount() {
            return operationAmount;
        }

        public void setOperationAmount(MoneyEntry operationAmount) {
            this.operationAmount = operationAmount;
        }
    }

}
