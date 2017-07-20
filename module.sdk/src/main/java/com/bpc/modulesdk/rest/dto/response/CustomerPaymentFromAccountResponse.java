package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/24/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPaymentFromAccountResponse extends MainResponse {
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

        public List<CustomerAccountEntry> getSelectSourceAccount() {
            return selectSourceAccount;
        }

        public void setSelectSourceAccount(List<CustomerAccountEntry> selectSourceAccount) {
            this.selectSourceAccount = selectSourceAccount;
        }
    }
}
