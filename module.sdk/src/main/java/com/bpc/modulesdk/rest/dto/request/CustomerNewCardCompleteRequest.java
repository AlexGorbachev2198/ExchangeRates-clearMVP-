package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerCardEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Siarhei Mikevich on 4/26/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerNewCardCompleteRequest extends StampedRequest {

    private String transRef;
    private DataResponse dataResponse;

    public CustomerNewCardCompleteRequest(String transRef, DataResponse dataResponse) {
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
        private CustomerCardEntry newPinBlock;

        public DataResponse(CustomerCardEntry newPinBlock, String agentPass) {
            this.agentPass = agentPass;
            this.newPinBlock = newPinBlock;
        }

        public String getAgentPass() {
            return agentPass;
        }

        public void setAgentPass(String agentPass) {
            this.agentPass = agentPass;
        }

        public CustomerCardEntry getNewPinBlock() {
            return newPinBlock;
        }

        public void setNewPinBlock(CustomerCardEntry newPinBlock) {
            this.newPinBlock = newPinBlock;
        }

    }

}
