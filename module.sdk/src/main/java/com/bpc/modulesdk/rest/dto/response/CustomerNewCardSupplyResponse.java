package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.AccountTypeEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Siarhei Mikevich on 4/26/17.
 */

public class CustomerNewCardSupplyResponse extends MainResponse {
    private String transRef;
    private RequiredData dataRequest;
    private OperationDetailsEntry operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public RequiredData getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(RequiredData dataRequest) {
        this.dataRequest = dataRequest;
    }

    public OperationDetailsEntry getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(OperationDetailsEntry operationDetails) {
        this.operationDetails = operationDetails;
    }

    public OperationConfirmationRequestEntry getConfirmationRequest() {
        return confirmationRequest;
    }

    public void setConfirmationRequest(OperationConfirmationRequestEntry confirmationRequest) {
        this.confirmationRequest = confirmationRequest;
    }

    public static class RequiredData implements Serializable {

        private List<AccountTypeEntry> selectAccountType;

        public List<AccountTypeEntry> getSelectAccountType() {
            return selectAccountType;
        }

        public void setSelectAccountType(List<AccountTypeEntry> selectAccountType) {
            this.selectAccountType = selectAccountType;
        }

    }

    public static class OperationDetailsEntry implements Serializable {

        private AccountTypeEntry accountType;
        private String accountCurrency;
        private String subagent;

        public AccountTypeEntry getAccountType() {
            return accountType;
        }

        public void setAccountType(AccountTypeEntry accountType) {
            this.accountType = accountType;
        }

        public String getAccountCurrency () {
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
