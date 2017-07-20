package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentInfoEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPaymentByCashRequest extends StampedRequest {
    private PaymentInfoEntry paymentInfo;
    private LocationEntry location;

    public CustomerPaymentByCashRequest() {
    }

    public CustomerPaymentByCashRequest(PaymentInfoEntry paymentInfo, LocationEntry location) {
        this.paymentInfo = paymentInfo;
        this.location = location;
    }

    public PaymentInfoEntry getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoEntry paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
