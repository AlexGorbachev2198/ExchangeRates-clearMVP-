package com.bpc.modulesdk.rest.dto.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class Account implements Serializable {

    public static final int STATUS_VALID = 0;
    public static final int STATUS_TEMP_BLOCK = 20;
    public static final int STATUS_PERM_BLOCK = 21;

    private String id;
    private String number;
    private String name;
    private Type type;
    private Status status;
    private BigDecimal balance;
    private String currency;
    private Active active = Active.NO;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isStateActive() {
        return getStatus() == Status.VALID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public static class AccountTypeComparator implements Comparator<Account> {
        @Override
        public int compare(Account lhs, Account rhs) {
            return Integer.valueOf(lhs.getType().toInt()).compareTo(rhs.getType().toInt());
        }
    }

    public enum Type implements Serializable {

        CHECKING(1), DEPOSIT(2), CREDIT(3);

        private int type;

        Type(int type) {
            this.type = type;
        }

        @JsonCreator
        public static Type fromInt(int value) {
            for (Type type : Type.values())
                if (type.toInt() == value)
                    return type;
            return null;
        }

        @JsonValue
        public int toInt() {
            return type;
        }

    }

    public enum Status implements Serializable {

        VALID(0), TEMP_BLOCK(20), PERM_BLOCK(21);

        private int status;

        Status(int status) {
            this.status = status;
        }

        @JsonCreator
        public static Status fromInt(int value) {
            for (Status type : Status.values())
                if (type.toInt() == value)
                    return type;
            return null;
        }

        @JsonValue
        public int toInt() {
            return status;
        }
    }

    public enum Active implements Serializable {

        YES(1), NO(0);

        private int value;

        Active(int value) {
            this.value = value;
        }

        @JsonCreator
        public static Active fromInt(int value) {
            for (Account.Active type : Account.Active.values())
                if (type.toInt() == value)
                    return type;
            return null;
        }

        @JsonValue
        public int toInt() {
            return value;
        }

    }
}

