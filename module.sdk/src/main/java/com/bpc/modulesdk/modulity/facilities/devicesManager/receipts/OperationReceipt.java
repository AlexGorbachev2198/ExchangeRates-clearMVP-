package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;
import com.bpc.modulesdk.utils.DateHelper;
import com.bpc.modulesdk.utils.MoneyUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dzmitrystrupinski on 3/23/17.
 */

public class OperationReceipt implements Serializable {

    private int receiptTitleId;
    private String operationDescription="";
    private boolean operationSuccessful;

    private Date operationDate;
    private String operationAmount="";

    private String agentName="";
    private String agentPhone="";
    private String agentTransactionId;

    private String additionalInfo="";

    private String errorDescription="";

    private String feeAmount="";
    private String totalAmount="";

    public int getReceiptTitleId() {
        return receiptTitleId;
    }

    public void setReceiptTitleId(int receiptTitleId) {
        this.receiptTitleId = receiptTitleId;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(String operationAmount) {
        this.operationAmount = operationAmount;
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

    public String getAgentTransactionId() {
        return agentTransactionId;
    }

    public void setAgentTransactionId(String agentTransactionId) {
        this.agentTransactionId = agentTransactionId;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public boolean isOperationSuccessful() {
        return operationSuccessful;
    }

    public void setOperationSuccessful(boolean operationSuccessful) {
        this.operationSuccessful = operationSuccessful;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public void setFeeAmount(MoneyEntry feeAmount) {
        String fee = null;
        if (feeAmount != null) {
            fee = feeAmount.getAmount() + " " + feeAmount.getCurrency();
        }
        this.feeAmount = fee;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalAmount(MoneyEntry totalAmount) {
        String total = null;
        if (totalAmount != null) {
            total = totalAmount.getAmount() + " " + totalAmount.getCurrency();
        }
        this.totalAmount = total;
    }

    public void populateReceiptWithCommonFields(OperationDetailsEntry operationDetails, boolean isSuccess) {
        Date operationDate = DateHelper.parseDateTime(operationDetails.getTimestamp());
        setOperationDate(operationDate);
        setOperationAmount(MoneyUtils.moneyString(operationDetails.getOperationAmount()));
        setAgentName(operationDetails.getAgentName());
        setAgentPhone(operationDetails.getAgentPhone());
        setAgentTransactionId(operationDetails.getAuthId());
        setAdditionalInfo(operationDetails.getAdditionalInfo());
        setOperationSuccessful(isSuccess);
    }
}
