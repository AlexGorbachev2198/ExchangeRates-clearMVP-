package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/17/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashToCashTransferSupplyRequest extends StampedRequest {
    private String transRef;
    private CustomerCashToCashTransferSupplyRequest.DataResponse dataResponse;

    public CustomerCashToCashTransferSupplyRequest(String transRef, CustomerCashToCashTransferSupplyRequest.DataResponse dataResponse) {
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
        private String agentAccount;
        private MoneyEntry operationAmount;
        private String senderPhoneNumber;
        private String receiverPhoneNumber;

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

        public String getSenderPhoneNumber() {
            return senderPhoneNumber;
        }

        public void setSenderPhoneNumber(String senderPhoneNumber) {
            this.senderPhoneNumber = senderPhoneNumber;
        }

        public String getReceiverPhoneNumber() {
            return receiverPhoneNumber;
        }

        public void setReceiverPhoneNumber(String receiverPhoneNumber) {
            this.receiverPhoneNumber = receiverPhoneNumber;
        }
    }
}
