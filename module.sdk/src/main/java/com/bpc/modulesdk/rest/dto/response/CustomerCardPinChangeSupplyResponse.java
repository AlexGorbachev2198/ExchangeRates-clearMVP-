package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.ErrorDescriptionEntry;

/**
 * Created by dzmitrystrupinski on 6/14/17.
 */

public class CustomerCardPinChangeSupplyResponse extends MainResponse {
    private ErrorDescriptionEntry errorDesc;
    private String transRef;

    public ErrorDescriptionEntry getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(ErrorDescriptionEntry errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }
}
