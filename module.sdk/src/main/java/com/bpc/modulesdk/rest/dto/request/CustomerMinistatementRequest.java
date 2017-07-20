package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Smolyaninov on 05.06.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerMinistatementRequest extends StampedRequest {

    CustomerCardEntry sourceCard;
    LocationEntry locationEntry;

    public CustomerMinistatementRequest(CustomerCardEntry sourceCard, LocationEntry locationEntry) {
        this.sourceCard = sourceCard;
        this.locationEntry = locationEntry;
    }

    public CustomerCardEntry getSourceCard() {
        return sourceCard;
    }

    public void setSourceCard(CustomerCardEntry sourceCard) {
        this.sourceCard = sourceCard;
    }

    public LocationEntry getLocationEntry() {
        return locationEntry;
    }

    public void setLocationEntry(LocationEntry locationEntry) {
        this.locationEntry = locationEntry;
    }
}
