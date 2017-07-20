package com.bpc.modulesdk.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 6/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCardPinChangeCompleteRequest extends StampedRequest {
    private String transRef;

    public CustomerCardPinChangeCompleteRequest() {
    }

    public CustomerCardPinChangeCompleteRequest(String transRef) {
        this.transRef = transRef;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }
}
