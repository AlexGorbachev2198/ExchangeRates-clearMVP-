package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by dzmitrystrupinski on 3/20/17.
 */

public class CustomerCashWithdrawalSupplyRequest extends StampedRequest {
    private String transRef;
    private ProvidedData dataResponse;

    public ProvidedData getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(ProvidedData dataResponse) {
        this.dataResponse = dataResponse;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public CustomerCashWithdrawalSupplyRequest(String transRef, ProvidedData providedData) {
        this.transRef = transRef;
        this.dataResponse = providedData;
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
