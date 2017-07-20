package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationConfirmationRequestEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.OperationDetailsEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Smolyaninov on 28.03.2017.
 */

public class CustomerAcctToAcctSupplyResponse extends MainResponse implements Serializable {

    private String transferRef;
    private RequiredData dataRequest;
    private OperationDetailsEntry operationDetails;
    private OperationConfirmationRequestEntry confirmationRequest;


    public RequiredData getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(RequiredData dataRequest) {
        this.dataRequest = dataRequest;
    }

    public String getTransferRef() {
        return transferRef;
    }

    public void setTransferRef(String transferRef) {
        this.transferRef = transferRef;
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

        private List<CustomerAccountEntry> selectTargetAccount;

        public RequiredData(List<CustomerAccountEntry> selectTargetAccount) {
            this.selectTargetAccount = selectTargetAccount;
        }

        public RequiredData() {
        }

        public List<CustomerAccountEntry> getSelectTargetAccount() {
            return selectTargetAccount;
        }

        public void setSelectTargetAccount(List<CustomerAccountEntry> selectTargetAccount) {
            this.selectTargetAccount = selectTargetAccount;
        }
    }
}
