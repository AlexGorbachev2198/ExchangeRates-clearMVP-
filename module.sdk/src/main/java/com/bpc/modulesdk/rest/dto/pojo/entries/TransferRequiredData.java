package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dzmitrystrupinski on 4/3/17.
 */

public class TransferRequiredData implements Serializable {

    private List<CustomerAccountEntry> selectSourceAccount;
    private List<CustomerAccountEntry> selectTargetAccount;
    private List<CustomerAccountEntry> selectAgentAccount;
    private boolean senderPhoneNumber;
    private boolean receiverPhoneNumber;

    public List<CustomerAccountEntry> getSelectSourceAccount() {
        return selectSourceAccount;
    }

    public void setSelectSourceAccount(List<CustomerAccountEntry> selectSourceAccount) {
        this.selectSourceAccount = selectSourceAccount;
    }

    public List<CustomerAccountEntry> getSelectTargetAccount() {
        return selectTargetAccount;
    }

    public void setSelectTargetAccount(List<CustomerAccountEntry> selectTargetAccount) {
        this.selectTargetAccount = selectTargetAccount;
    }

    public boolean isSenderPhoneNumber() {
        return this.senderPhoneNumber;
    }

    public void setSenderPhoneNumber(boolean senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public boolean isReceiverPhoneNumber() {
        return this.receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(boolean receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public List<CustomerAccountEntry> getSelectAgentAccount() {
        return selectAgentAccount;
    }

    public void setSelectAgentAccount(List<CustomerAccountEntry> selectAgentAccount) {
        this.selectAgentAccount = selectAgentAccount;
    }
}
