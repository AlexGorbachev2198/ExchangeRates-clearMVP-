package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dmolst on 3/17/17.
 */

public class CustomerCashWithdrawalRequiredData implements Serializable {
    private List<CustomerAccountEntry> selectCardAccount;
    private List<CustomerAccountEntry> selectAgentAccount;

    public List<CustomerAccountEntry> getSelectCardAccount() {
        return selectCardAccount;
    }

    public void setSelectCardAccount(List<CustomerAccountEntry> selectCardAccount) {
        this.selectCardAccount = selectCardAccount;
    }

    public List<CustomerAccountEntry> getSelectAgentAccount() {
        return selectAgentAccount;
    }

    public void setSelectAgentAccount(List<CustomerAccountEntry> selectAgentAccount) {
        this.selectAgentAccount = selectAgentAccount;
    }
}
