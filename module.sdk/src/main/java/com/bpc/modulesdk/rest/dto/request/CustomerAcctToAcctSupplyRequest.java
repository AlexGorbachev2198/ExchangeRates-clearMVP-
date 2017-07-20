package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Smolyaninov on 28.03.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAcctToAcctSupplyRequest extends StampedRequest {

    private CustomerAcctToAcctSupplyRequest.DataResponse dataResponse;
    private String transRef;

    public CustomerAcctToAcctSupplyRequest(String transRef, CustomerAcctToAcctSupplyRequest.DataResponse dataResponse) {
        this.dataResponse = dataResponse;
        this.transRef = transRef;
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
        private String targetCard;
        private MoneyEntry operationAmount;

        public DataResponse(String sourceAccount, String targetAccount, String targetCard, MoneyEntry operationAmount) {
            this.sourceAccount = sourceAccount;
            this.targetAccount = targetAccount;
            this.operationAmount = operationAmount;
            this.targetCard = targetCard;
        }

        public String getTargetCard() {
            return targetCard;
        }

        public void setTargetCard(String targetCard) {
            this.targetCard = targetCard;
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

        public MoneyEntry getOperationAmount() {
            return operationAmount;
        }

        public void setOperationAmount(MoneyEntry operationAmount) {
            this.operationAmount = operationAmount;
        }
    }
}
