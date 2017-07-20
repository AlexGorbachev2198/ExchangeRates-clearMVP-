package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCashWithdrawalRequiredData;

/**
 * Created by dmolst on 3/17/17.
 */

public class CustomerCashWithdrawalResponse extends MainResponse {
    private String transRef;
    private CustomerCashWithdrawalRequiredData dataRequest;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public CustomerCashWithdrawalRequiredData getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(CustomerCashWithdrawalRequiredData dataRequest) {
        this.dataRequest = dataRequest;
    }
}
