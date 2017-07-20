package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.AccountTypeEntry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dzmitrystrupinski on 4/20/17.
 */

public class CustomerNewAccountResponse extends MainResponse {
    private String transRef;
    private RequiredData dataRequest;

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

    public static class RequiredData implements Serializable {
        private List<AccountTypeEntry> selectAccountType;
        private boolean subagentAllowed;

        public List<AccountTypeEntry> getSelectAccountType() {
            return selectAccountType;
        }

        public void setSelectAccountType(List<AccountTypeEntry> selectAccountType) {
            this.selectAccountType = selectAccountType;
        }

        public boolean isSubagentAllowed() {
            return subagentAllowed;
        }

        public void setSubagentAllowed(boolean subagentAllowed) {
            this.subagentAllowed = subagentAllowed;
        }
    }
}
