package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by dzmitrystrupinski on 4/3/17.
 */

public class AgentAcctToAcctTransferSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public AgentAcctToAcctTransferSupplyRequest(String transRef, DataResponse dataResponse) {
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
        private String targetAccount;
        private MoneyEntry operationAmount;

        public MoneyEntry getOperationAmount() {
            return operationAmount;
        }

        public void setOperationAmount(MoneyEntry operationAmount) {
            this.operationAmount = operationAmount;
        }

        public String getSourceAccount() {
            return sourceAccount;
        }

        public void setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
        }

        public String getTargetAccount() {
            return targetAccount;
        }

        public void setTargetAccount(String targetAccount) {
            this.targetAccount = targetAccount;
        }
    }
}
