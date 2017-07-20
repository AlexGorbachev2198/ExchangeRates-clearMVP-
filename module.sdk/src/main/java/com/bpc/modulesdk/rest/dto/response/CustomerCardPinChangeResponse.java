package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.ErrorDescriptionEntry;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 6/14/17.
 */

public class CustomerCardPinChangeResponse extends MainResponse {
    private ErrorDescriptionEntry errorDesc;
    private String transRef;
    private RequiredData dataRequest;

    public void setErrorDesc(ErrorDescriptionEntry errorDesc) {
        this.errorDesc = errorDesc;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public void setDataRequest(RequiredData dataRequest) {
        this.dataRequest = dataRequest;
    }

    public ErrorDescriptionEntry getErrorDesc() {
        return errorDesc;
    }

    public String getTransRef() {
        return transRef;
    }

    public RequiredData getDataRequest() {
        return dataRequest;
    }

    public class RequiredData implements Serializable {
        private boolean cardPinRequired;
        private boolean newPinRequired;

        public RequiredData() {
        }

        public RequiredData(boolean cardPinRequired, boolean newPinRequired) {
            this.cardPinRequired = cardPinRequired;
            this.newPinRequired = newPinRequired;
        }

        public boolean isCardPinRequired() {
            return cardPinRequired;
        }

        public boolean isNewPinRequired() {
            return newPinRequired;
        }

    }
}
