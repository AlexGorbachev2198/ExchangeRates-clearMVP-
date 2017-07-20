package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 5/2/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCustomerRequest extends StampedRequest {
    private CustomerCardEntry newCard;
    private LocationEntry location;

    public NewCustomerRequest(CustomerCardEntry newCard, LocationEntry location) {
        this.newCard = newCard;
        this.location = location;
    }

    public CustomerCardEntry getNewCard() {
        return newCard;
    }

    public void setNewCard(CustomerCardEntry newCard) {
        this.newCard = newCard;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
