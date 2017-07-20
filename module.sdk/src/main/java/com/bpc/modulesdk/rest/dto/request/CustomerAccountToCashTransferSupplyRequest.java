package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Siarhei Mikevich on 4/4/17.
 */

public class CustomerAccountToCashTransferSupplyRequest extends StampedRequest {

    private String transRef;
    private DataResponse dataResponse;

    public CustomerAccountToCashTransferSupplyRequest(String transRef, DataResponse dataResponse) {
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
        private MoneyEntry operationAmount;
        private String senderPhoneNumber;
        private String receiverPhoneNumber;

        public String getSourceAccount() {
            return sourceAccount;
        }

        public void setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
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
