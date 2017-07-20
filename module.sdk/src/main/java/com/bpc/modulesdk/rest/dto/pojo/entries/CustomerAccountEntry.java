package com.bpc.modulesdk.rest.dto.pojo.entries;

import com.bpc.modulesdk.rest.dto.pojo.Account;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Smolyaninov on 21.03.2017.
 */

public class CustomerAccountEntry implements Serializable {

    private String id;
    private String number;
    private String name;
    private CustomerAccountEntry.Type type;
    private String currency;
    private MoneyEntry availableBalance;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CustomerAccountEntry.Type getType() {
        return type;
    }

    public void setType(CustomerAccountEntry.Type type) {
        this.type = type;
    }

    public MoneyEntry getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(MoneyEntry availableBalance) {
        this.availableBalance = availableBalance;
    }

    public static class AccountTypeComparator implements Comparator<Account> {
        @Override
        public int compare(Account lhs, Account rhs) {
            return Integer.valueOf(lhs.getType().toInt()).compareTo(rhs.getType().toInt());
        }
    }

    public enum Type implements Serializable {

        CHECKING("checking"), DEPOSIT("deposit"), CREDIT("credit");

        private String type;

        Type(String type) {
            this.type = type;
        }

        @JsonCreator
        public static CustomerAccountEntry.Type fromString(String value) {
            for (CustomerAccountEntry.Type type : CustomerAccountEntry.Type.values()) {
                String typeString = type.toString();
                if (typeString.equalsIgnoreCase(value.trim())) {
                    return type;
                }
            }

            return null;
        }

        @JsonValue
        public String toString() {
            return type;
        }

        public int toInt() {
            int result;
            switch (type) {
                case "checking" : result = 1; break;
                case "deposit" : result = 2; break;
                case "credit" : result = 3; break;
                default: result = -1; break;
            }

            //TODO to enum

            return result;
        }
    }
}