package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dzmitrystrupinski on 6/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCardPinChangeSupplyRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public CustomerCardPinChangeSupplyRequest() {
    }

    public CustomerCardPinChangeSupplyRequest(String transRef, DataResponse dataResponse) {
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
        private CustomerCardEntry cardPinBlock;
        private CustomerCardEntry newPinBlock;

        public DataResponse() {
        }

        public DataResponse(CustomerCardEntry cardPinBlock, CustomerCardEntry newPinBlock) {
            this.cardPinBlock = cardPinBlock;
            this.newPinBlock = newPinBlock;
        }

        public CustomerCardEntry getCardPinBlock() {
            return cardPinBlock;
        }

        public CustomerCardEntry getNewPinBlock() {
            return newPinBlock;
        }

        public void setCardPinBlock(CustomerCardEntry cardPinBlock) {
            this.cardPinBlock = cardPinBlock;
        }

        public void setNewPinBlock(CustomerCardEntry newPinBlock) {
            this.newPinBlock = newPinBlock;
        }

    }
}
