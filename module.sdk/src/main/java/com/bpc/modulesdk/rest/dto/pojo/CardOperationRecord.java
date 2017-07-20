package com.bpc.modulesdk.rest.dto.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smolyaninov on 14.06.2017.
 */

public class CardOperationRecord extends OperationRecord {

    private BigDecimal amount;
    private String currency;
    private List<OperationStatus> operationStatuses;
    private BigDecimal feeAmount;
    private String feeCurrency;

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<OperationStatus> getOperationStatuses() {
        return operationStatuses == null ? new ArrayList<OperationStatus>() : operationStatuses;
    }

    public void setOperationStatuses(List<OperationStatus> operationStatuses) {
        this.operationStatuses = operationStatuses;
    }
}
