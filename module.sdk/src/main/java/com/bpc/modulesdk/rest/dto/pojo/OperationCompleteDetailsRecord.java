package com.bpc.modulesdk.rest.dto.pojo;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

import java.io.Serializable;

/**
 * Created by Smolyaninov on 30.03.2017.
 */

public class OperationCompleteDetailsRecord implements Serializable {

    private DatetimeParameter timestamp;
    private MoneyEntry operationAmount;
    private MoneyEntry totalAmount;

    private String sourceCardNumber;
    private String targetCardNumber;

    private String sourceAccountNumber;
    private String targetAccountNumber;

    private MoneyEntry agentFee;
    private MoneyEntry customerFee;

    private MoneyEntry agentOriginalBalance;
    private MoneyEntry agentFinalBalance;

    private String agentName;
    private String agentPhone;

    private String authId;
    private String additionalInfo;


    public DatetimeParameter getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DatetimeParameter timestamp) {
        this.timestamp = timestamp;
    }

    public MoneyEntry getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(MoneyEntry operationAmount) {
        this.operationAmount = operationAmount;
    }

    public MoneyEntry getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(MoneyEntry totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSourceCardNumber() {
        return sourceCardNumber;
    }

    public void setSourceCardNumber(String sourceCardNumber) {
        this.sourceCardNumber = sourceCardNumber;
    }

    public String getTargetCardNumber() {
        return targetCardNumber;
    }

    public void setTargetCardNumber(String targetCardNumber) {
        this.targetCardNumber = targetCardNumber;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }

    public MoneyEntry getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(MoneyEntry agentFee) {
        this.agentFee = agentFee;
    }

    public MoneyEntry getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(MoneyEntry customerFee) {
        this.customerFee = customerFee;
    }

    public MoneyEntry getAgentOriginalBalance() {
        return agentOriginalBalance;
    }

    public void setAgentOriginalBalance(MoneyEntry agentOriginalBalance) {
        this.agentOriginalBalance = agentOriginalBalance;
    }

    public MoneyEntry getAgentFinalBalance() {
        return agentFinalBalance;
    }

    public void setAgentFinalBalance(MoneyEntry agentFinalBalance) {
        this.agentFinalBalance = agentFinalBalance;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
