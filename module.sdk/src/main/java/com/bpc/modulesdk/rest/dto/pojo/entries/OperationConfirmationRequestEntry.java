package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 3/20/17.
 */

public class OperationConfirmationRequestEntry implements Serializable {
    private boolean agentPassRequired;
    private boolean cardPinRequired;
    private boolean newPinRequired;

    public OperationConfirmationRequestEntry() {
    }

    public OperationConfirmationRequestEntry(boolean agentPassRequired, boolean cardPinRequired) {
        this.agentPassRequired = agentPassRequired;
        this.cardPinRequired = cardPinRequired;
    }

    public boolean isAgentPassRequired() {
        return agentPassRequired;
    }

    public void setAgentPassRequired(boolean agentPassRequired) {
        this.agentPassRequired = agentPassRequired;
    }

    public boolean isCardPinRequired() {
        return cardPinRequired;
    }

    public void setCardPinRequired(boolean cardPinRequired) {
        this.cardPinRequired = cardPinRequired;
    }

    public boolean isNewPinRequired() {
        return newPinRequired;
    }

    public void setNewPinRequired(boolean newPinRequired) {
        this.newPinRequired = newPinRequired;
    }
}
