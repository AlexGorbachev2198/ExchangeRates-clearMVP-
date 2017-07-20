package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Siarhei Mikevich on 4/26/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerNewCardRequest extends StampedRequest {

    private CustomerCardEntry customerCard;
    private LocationEntry location;

    public CustomerNewCardRequest(CustomerCardEntry customerCard, LocationEntry location) {
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
