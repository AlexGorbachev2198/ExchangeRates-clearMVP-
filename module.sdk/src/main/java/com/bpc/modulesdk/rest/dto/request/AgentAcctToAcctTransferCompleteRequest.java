package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by dzmitrystrupinski on 4/3/17.
 */

public class AgentAcctToAcctTransferCompleteRequest extends StampedRequest {
    private String transRef;
    private DataResponse dataResponse;

    public AgentAcctToAcctTransferCompleteRequest(String transRef, DataResponse dataResponse) {
        this.transRef = transRef;
        this.dataResponse = dataResponse;
    }

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public static class DataResponse {
        private String agentPass;

        public String getAgentPass() {
            return agentPass;
        }

        public void setAgentPass(String agentPass) {
            this.agentPass = agentPass;
        }
    }
}
