package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 4/12/17.
 */

public class OperationConfirmationProvidedDataEntry implements Serializable {

    private String agentPass;
    private CustomerCardEntry cardPinBlock;
    private CustomerCardEntry newPinBlock;

    public String getAgentPass() {
        return agentPass;
    }

    public void setAgentPass(String agentPass) {
        this.agentPass = agentPass;
    }

    public CustomerCardEntry getCardPinBlock() {
        return cardPinBlock;
    }

    public void setCardPinBlock(CustomerCardEntry cardPinBlock) {
        this.cardPinBlock = cardPinBlock;
    }

    public CustomerCardEntry getNewPinBlock() {
        return newPinBlock;
    }

    public void setNewPinBlock(CustomerCardEntry newPinBlock) {
        this.newPinBlock = newPinBlock;
    }


}
