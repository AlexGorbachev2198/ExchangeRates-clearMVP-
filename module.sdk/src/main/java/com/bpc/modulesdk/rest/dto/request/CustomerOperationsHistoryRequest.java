package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationInfoEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/25/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerOperationsHistoryRequest extends StampedRequest {
    private CustomerCardEntry customerCard;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<OperationInfoEntry.OperationType> operationTypes;

    public CustomerOperationsHistoryRequest() {
    }

    public CustomerOperationsHistoryRequest(CustomerCardEntry customerCard, List<OperationInfoEntry.OperationType> operationTypes) {
        this.customerCard = customerCard;
        this.operationTypes = operationTypes;
    }

    public CustomerCardEntry getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(CustomerCardEntry customerCard) {
        this.customerCard = customerCard;
    }

    public List<OperationInfoEntry.OperationType> getOperationTypes() {
        return operationTypes;
    }

    public void setOperationTypes(List<OperationInfoEntry.OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }
}
