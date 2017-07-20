package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.Account;

import java.util.List;

/**
 * Created by Smolyaninov on 24.01.2017.
 */

public class AccountsResponse extends MainResponse {

    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
