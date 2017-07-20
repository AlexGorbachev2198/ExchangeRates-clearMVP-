package com.bpc.modulesdk.rest.dto.pojo.entries;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CustomerBalanceRequiredData {

    private boolean provideAgentPass;
    private List<Account> selectAccount;

    public boolean isProvideAgentPass() {
        return provideAgentPass;
    }

    public void setProvideAgentPass(boolean provideAgentPass) {
        this.provideAgentPass = provideAgentPass;
    }

    public List<Account> getSelectAccount() {
        return selectAccount;
    }

    public void setSelectAccount(List<Account> selectAccount) {
        this.selectAccount = selectAccount;
    }

    @JsonIgnore
    @Nullable
    public String findAccountById(String id) {
        if (!TextUtils.isEmpty(id)) {
            if (selectAccount != null && !selectAccount.isEmpty()) {
                for (Account account : selectAccount) {
                    if (id.equals(account.getId())) return account.getNumber();
                }
            }
        }
        return null;

    }

    public static class Account {

        private String id;
        private String number;
        private String name;

        @JsonIgnore
        public Account(String id, String number, String name) {
            this.id = id;
            this.number = number;
            this.name = name;
        }

        public Account() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}