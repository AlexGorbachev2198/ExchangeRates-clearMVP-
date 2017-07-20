package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Siarhei Mikevich on 6/26/17.
 */

public class CustomerRepaymentCashToLoanResponse extends MainResponse {

    private String transRef;
    private RequiredData dataRequest;

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public RequiredData getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(RequiredData dataRequest) {
        this.dataRequest = dataRequest;
    }

    public class RequiredData implements Serializable {

        private List<CustomerAccountEntry> selectSourceAccount;

        public RequiredData() {
        }

        public RequiredData(List<CustomerAccountEntry> selectSourceAccount) {
            this.selectSourceAccount = selectSourceAccount;
        }

        public List<CustomerAccountEntry> getSelectSourceAccount() {
            return selectSourceAccount;
        }

    }

}
