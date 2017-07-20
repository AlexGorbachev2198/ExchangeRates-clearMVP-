package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/11/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashToAccountTransferSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public CustomerCashToAccountTransferSupplyRequest(String transRef, CustomerCashToAccountTransferSupplyRequest.DataResponse dataResponse) {
        this.transRef = transRef;
        this.dataResponse = dataResponse;
    }

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
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
