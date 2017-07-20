package com.bpc.modulesdk.rest.dto.response;


import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;

import java.io.Serializable;

/**
 * Created by Masloed on 13.01.2017.
 */
public class CustomerBalanceCompleteResponse extends MainResponse implements Serializable{

    private OperationDetailsEntry operationDetails;
    private boolean agentReceiptAvailable;
    private boolean customerReceiptAvailable;

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
        return this.customerReceiptAvailable;
    }

    public void setCustomerReceiptAvailable(boolean customerReceiptAvailable) {
        this.customerReceiptAvailable = customerReceiptAvailable;
    }

}
