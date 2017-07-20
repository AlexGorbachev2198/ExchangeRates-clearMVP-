package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.RepaymentInfoEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 6/27/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRepaymentFromAccountSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public CustomerRepaymentFromAccountSupplyRequest() {
    }

    public CustomerRepaymentFromAccountSupplyRequest(String transRef, DataResponse dataResponse) {
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

    public static class DataResponse implements Serializable {
        private String sourceAccount;
        private RepaymentInfoEntry repaymentInfo;

        public String getSourceAccount() {
            return sourceAccount;
        }

        public void setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
        }

        public RepaymentInfoEntry getRepaymentInfo() {
            return repaymentInfo;
        }

        public void setRepaymentInfo(RepaymentInfoEntry repaymentInfo) {
            this.repaymentInfo = repaymentInfo;
        }
    }
}
