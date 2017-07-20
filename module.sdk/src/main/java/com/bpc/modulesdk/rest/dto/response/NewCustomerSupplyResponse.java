package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.AccountTypeEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.IdentityDocumentInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 5/2/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCustomerSupplyResponse extends MainResponse {
    private String transRef;
    private OperationDetails operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;

    public NewCustomerSupplyResponse() {
    }

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
        private CustomerInfoEntry customerInfo;
        private IdentityDocumentInfoEntry idocInfo;

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

        public CustomerInfoEntry getCustomerInfo() {
            return customerInfo;
        }

        public void setCustomerInfo(CustomerInfoEntry customerInfo) {
            this.customerInfo = customerInfo;
        }

        public IdentityDocumentInfoEntry getIdocInfo() {
            return idocInfo;
        }

        public void setIdocInfo(IdentityDocumentInfoEntry idocInfo) {
            this.idocInfo = idocInfo;
        }
    }
}
