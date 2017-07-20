package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 6/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationCompleteResponse extends MainResponse {
    private OperationDetailsEntry operationDetails;
    private boolean agentReceiptAvailable = true;
    private boolean customerReceiptAvailable = true;

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
}
