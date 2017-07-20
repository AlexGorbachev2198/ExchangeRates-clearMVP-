package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Smolyaninov on 11.04.2017.
 */

public class CustomerCashReceiveSupplyRequest extends StampedRequest {

    private CustomerCashReceiveSupplyRequest.DataResponse dataResponse = new DataResponse();
    private String transRef;

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

    public CustomerCashReceiveSupplyRequest(String agentAccount, String transRef) {
        this.dataResponse.setAgentAccount(agentAccount);
        this.transRef = transRef;
    }

    public static class DataResponse {
        private String agentAccount;

        public String getAgentAccount() {
            return agentAccount;
        }

        public void setAgentAccount(String agentAccount) {
            this.agentAccount = agentAccount;
        }
    }
}
