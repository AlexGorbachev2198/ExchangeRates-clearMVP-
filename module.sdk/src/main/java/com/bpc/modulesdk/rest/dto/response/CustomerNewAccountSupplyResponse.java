package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.AccountTypeEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 4/20/17.
 */

public class CustomerNewAccountSupplyResponse extends MainResponse {
    private String transRef;
    private OperationDetails operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public OperationDetails getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(OperationDetails operationDetails) {
        this.operationDetails = operationDetails;
    }

    public OperationConfirmationRequestEntry getConfirmationRequest() {
        return confirmationRequest;
    }

    public void setConfirmationRequest(OperationConfirmationRequestEntry confirmationRequest) {
        this.confirmationRequest = confirmationRequest;
    }

    public class OperationDetails implements Serializable {
        private AccountTypeEntry accountType;
        private String accountCurrency;
        private String subagent;

        public OperationDetails() {
        }

        public OperationDetails(AccountTypeEntry accountType, String accountCurrency, String subagent) {
            this.accountType = accountType;
            this.accountCurrency = accountCurrency;
            this.subagent = subagent;
        }

        public AccountTypeEntry getAccountType() {
            return accountType;
        }

        public void setAccountType(AccountTypeEntry accountType) {
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
