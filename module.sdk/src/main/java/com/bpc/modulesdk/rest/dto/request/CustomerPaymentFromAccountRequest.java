package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 5/24/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPaymentFromAccountRequest extends StampedRequest {
    private CustomerCardEntry sourceCard;
    private LocationEntry location;

    public CustomerPaymentFromAccountRequest() {
    }

    public CustomerPaymentFromAccountRequest(CustomerCardEntry sourceCard, LocationEntry location) {
        this.sourceCard = sourceCard;
        this.location = location;
    }

    public CustomerCardEntry getSourceCard() {
        return sourceCard;
    }

    public void setSourceCard(CustomerCardEntry sourceCard) {
        this.sourceCard = sourceCard;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
