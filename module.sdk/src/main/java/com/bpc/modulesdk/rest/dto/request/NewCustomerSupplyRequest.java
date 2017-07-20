package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerInfoEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.IdentityDocumentInfoEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 5/2/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCustomerSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public NewCustomerSupplyRequest(String transRef, DataResponse dataResponse) {
        this.transRef = transRef;
        this.dataResponse = dataResponse;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    public static class DataResponse {
        private String accountType;
        private String accountCurrency;
        private String subagent;
        private CustomerInfoEntry customerInfo;
        private IdentityDocumentInfoEntry idocInfo;

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getAccountCurrency() {
            return accountCurrency;
        }

        public void setAccountCurrency(String accountCurrency) {
            this.accountCurrency = accountCurrency;
        }

        public String getSubagent() {
            return subagent;
        }

        public void setSubagent(String subagent) {
            this.subagent = subagent;
        }

        public CustomerInfoEntry getCustomerInfo() {
            return customerInfo;
        }

        public void setCustomerInfo(CustomerInfoEntry customerInfo) {
            this.customerInfo = customerInfo;
        }

        public IdentityDocumentInfoEntry getIdocInfo() {
            return idocInfo;
        }

        public void setIdocInfo(IdentityDocumentInfoEntry idocInfo) {
            this.idocInfo = idocInfo;
        }
    }
}
