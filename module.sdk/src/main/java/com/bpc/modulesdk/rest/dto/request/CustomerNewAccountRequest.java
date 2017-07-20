package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 4/20/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerNewAccountRequest extends StampedRequest {
    private CustomerCardEntry customerCard;
    private LocationEntry location;

    public CustomerNewAccountRequest(CustomerCardEntry customerCard, LocationEntry location) {
        this.customerCard = customerCard;
        this.location = location;
    }

    public CustomerCardEntry getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(CustomerCardEntry customerCard) {
        this.customerCard = customerCard;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
