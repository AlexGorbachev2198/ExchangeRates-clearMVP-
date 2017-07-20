package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.RepaymentInfoEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Siarhei Mikevich on 6/26/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRepaymentCashToLoanRequest extends StampedRequest {

    private RepaymentInfoEntry repaymentInfo;
    private LocationEntry location;

    public CustomerRepaymentCashToLoanRequest(RepaymentInfoEntry repaymentInfo, LocationEntry location) {
        this.repaymentInfo = repaymentInfo;
        this.location = location;
    }

    public RepaymentInfoEntry getRepaymentInfo() {
        return repaymentInfo;
    }

    public void setRepaymentInfo(RepaymentInfoEntry repaymentInfo) {
        this.repaymentInfo = repaymentInfo;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }

}
