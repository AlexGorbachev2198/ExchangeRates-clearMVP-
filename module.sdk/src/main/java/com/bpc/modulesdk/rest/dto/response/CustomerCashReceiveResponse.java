package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Smolyaninov on 10.04.2017.
 */

public class CustomerCashReceiveResponse extends MainResponse implements Serializable {


    private RequiredData dataRequest;
    private OperationDetailsEntry operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;
    private String transRef;


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

    public OperationDetailsEntry getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(OperationDetailsEntry operationDetails) {
        this.operationDetails = operationDetails;
    }

    public OperationConfirmationRequestEntry getConfirmationRequest() {
        return confirmationRequest;
    }

    public void setConfirmationRequest(OperationConfirmationRequestEntry confirmationRequest) {
        this.confirmationRequest = confirmationRequest;
    }

    public static class RequiredData implements Serializable {
        private List<CustomerAccountEntry> selectAgentAccount;
        private boolean receiverPhoneNumber;


        public List<CustomerAccountEntry> getSelectAgentAccount() {
            return selectAgentAccount;
        }

        public void setSelectAgentAccount(List<CustomerAccountEntry> selectAgentAccount) {
            this.selectAgentAccount = selectAgentAccount;
        }

        public boolean isReceiverPhoneNumber() {
            return receiverPhoneNumber;
        }

        public void setReceiverPhoneNumber(boolean receiverPhoneNumber) {
            this.receiverPhoneNumber = receiverPhoneNumber;
        }
    }
}
