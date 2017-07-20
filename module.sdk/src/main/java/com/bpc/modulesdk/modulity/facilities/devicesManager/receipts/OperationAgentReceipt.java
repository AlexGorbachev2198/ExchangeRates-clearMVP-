package com.bpc.modulesdk.modulity.facilities.devicesManager.receipts;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by dzmitrystrupinski on 3/23/17.
 */

public class OperationAgentReceipt extends OperationReceipt {

    private String comissionAmount;
    private String beginningBalance;
    private String endingBalance;

    public int getReceiptTitleId() {
        return R.string.agent_receipt;
    }

    public String getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(String endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(String beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public String getComissionAmount() {
        return comissionAmount;
    }

    public void setComissionAmount(String comissionAmount) {
        this.comissionAmount = comissionAmount;
    }

    public void setComissionAmount(MoneyEntry agentFee) {
        String agentComission = null;
        if (agentFee != null) {
            agentComission = agentFee.getAmount() + " " + agentFee.getCurrency();
        }
        this.comissionAmount = agentComission;
    }
}
