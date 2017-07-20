package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentInfoEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 5/24/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPaymentFromAccountSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public CustomerPaymentFromAccountSupplyRequest() {
    }

    public CustomerPaymentFromAccountSupplyRequest(String transRef, DataResponse dataResponse) {
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
        private String sourceAccount;
        private PaymentInfoEntry paymentInfo;

        public DataResponse() {
        }

        public DataResponse(String sourceAccount, PaymentInfoEntry paymentInfo) {
            this.sourceAccount = sourceAccount;
            this.paymentInfo = paymentInfo;
        }

        public String getSourceAccount() {
            return sourceAccount;
        }

        public void setSourceAccount(String sourceAccount) {
            this.sourceAccount = sourceAccount;
        }

        public PaymentInfoEntry getPaymentInfo() {
            return paymentInfo;
        }

        public void setPaymentInfo(PaymentInfoEntry paymentInfo) {
            this.paymentInfo = paymentInfo;
        }
    }
}
