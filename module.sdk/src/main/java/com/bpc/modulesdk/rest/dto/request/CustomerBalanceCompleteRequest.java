package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Masloed on 13.01.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerBalanceCompleteRequest extends StampedRequest{

    private String transRef;
    private DataResponse dataResponse;

    public CustomerBalanceCompleteRequest() {

    }

    public CustomerBalanceCompleteRequest(String transRef, DataResponse dataResponse) {
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

        private String agentPass;
        private CustomerCardEntry cardPinBlock;

        public String getAgentPass() {
            return agentPass;
        }

        public void setAgentPass(String agentPass) {
            this.agentPass = agentPass;
        }

        public CustomerCardEntry getCardPinBlock() {
            return cardPinBlock;
        }

        public void setCardPinBlock(CustomerCardEntry cardPinBlock) {
            this.cardPinBlock = cardPinBlock;
        }

    }

}
