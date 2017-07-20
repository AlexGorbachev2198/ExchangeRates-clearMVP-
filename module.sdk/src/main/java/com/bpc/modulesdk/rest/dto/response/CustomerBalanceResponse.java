package com.bpc.modulesdk.rest.dto.response;


import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Masloed on 13.01.2017.
 */
public class CustomerBalanceResponse extends MainResponse implements Serializable {

    private String transRef;
    private CustomerBalanceDataRequest dataRequest;
    private OperationConfirmationRequestEntry confirmationRequest;
    private OperationDetailsEntry operationDetails;
    private boolean agentReceiptAvailable;
    private boolean customerReceiptAvailable;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public CustomerBalanceDataRequest getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(CustomerBalanceDataRequest dataRequest) {
        this.dataRequest = dataRequest;
    }

    public OperationConfirmationRequestEntry getConfirmationRequest() {
        return confirmationRequest;
    }

    public void setConfirmationRequest(OperationConfirmationRequestEntry confirmationRequest) {
        this.confirmationRequest = confirmationRequest;
    }

    public OperationDetailsEntry getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(OperationDetailsEntry operationDetails) {
        this.operationDetails = operationDetails;
    }

    public boolean isAgentReceiptAvailable() {
        return agentReceiptAvailable;
    }

    public void setAgentReceiptAvailable(boolean agentReceiptAvailable) {
        this.agentReceiptAvailable = agentReceiptAvailable;
    }

    public boolean isCustomerReceiptAvailable() {
        return customerReceiptAvailable;
    }

    public void setCustomerReceiptAvailable(boolean customerReceiptAvailable) {
        this.customerReceiptAvailable = customerReceiptAvailable;
    }

    public static class CustomerBalanceDataRequest implements Serializable {

        private List<CustomerAccountEntry> selectAccount;

        public List<CustomerAccountEntry> getSelectAccount() {
            return selectAccount;
        }

        public void setSelectAccount(List<CustomerAccountEntry> selectAccount) {
            this.selectAccount = selectAccount;
        }
    }

}
