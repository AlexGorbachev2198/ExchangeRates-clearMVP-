package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dmolst on 3/17/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCashWithdrawalRequest extends StampedRequest {
    private CustomerCardEntry customerCard;
    private LocationEntry location;

    public CustomerCashWithdrawalRequest(CustomerCardEntry customerCard) {
        this.customerCard = customerCard;
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
