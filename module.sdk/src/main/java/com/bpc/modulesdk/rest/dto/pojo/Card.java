package com.bpc.modulesdk.rest.dto.pojo;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class Card implements Serializable {

    public static final int CARD_NUMBER_MIN_LENGHT = 13;
    public static final int CARD_NUMBER_MAX_LENGHT = 19;

    private String id = "";
    private String number = "";//Номер карты. Может быть маскирован.
    private String name = "";
    private Type type;
    private String brand = "";//optional
    private Status status = Status.UNKNOWN;
    private BigDecimal balance;//Доступный баланс по карте.//float????
    private String currency;//Альфа-3 код ISO-4217 основной валюты карты.
    private String expDate = "";//optional
    private Active active = Active.NO;
    private boolean pinNotSet = false;
    private List<String> linkedAccounts = Collections.emptyList();
    private Category category;

    //===========Getters & Setters==================/
    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public
    @NonNull
    List<String> getLinkedAccounts() {
        return linkedAccounts;
    }

    public void setLinkedAccounts(List<String> linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isPinNotSet() {
        return pinNotSet;
    }

    public void setPinNotSet(boolean pinNotSet) {
        this.pinNotSet = pinNotSet;
    }


    //============Public methods===============
    @JsonIgnore
    public boolean isStateActive() {
        return getStatus() == Status.VALID;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return id.isEmpty();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static class TypeComparator implements Comparator<Card> {
        CardTypeComparator cardTypeComparator = new CardTypeComparator();

        @Override
        public int compare(Card lhs, Card rhs) {
            int compareCardType = cardTypeComparator.compare(lhs, rhs);
            if (compareCardType == 0)
                return Integer.valueOf(lhs.getType().toInt()).compareTo(rhs.getType().toInt());
            else
                return compareCardType;
        }
    }

    public static class CardTypeComparator implements Comparator<Card> {
        @Override
        public int compare(Card lhs, Card rhs) {
            return getOrder(lhs.getCategory()).compareTo(getOrder(rhs.getCategory()));
        }

        private Integer getOrder(Category category) {
            if (category == null)
                category = Category.REGULAR;
            switch (category) {
                case VIRTUAL:
                    return 2;
                case EXTERNAL:
                    return 3;
                default:
                    return 1;
            }
        }
    }

    public static class CardActiveComparator implements Comparator<Card> {
        @Override
        public int compare(Card lhs, Card rhs) {
            return Integer.valueOf(rhs.getActive().toInt()).compareTo(lhs.getActive().toInt());
        }
    }

    @JsonIgnore
    public static Card createEmptyCard() {
        Card card = new Card();
        card.setId("");
        return card;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Card: ")
                .append("id: ").append(id).append(",")
                .append("number: ").append(number).append(",")
                .append("name: ").append(name).append(",")
                .append("type: ").append(type).append(",")
                .append("brand: ").append(brand).append(",")
                .append("status: ").append(status).append(",")
                .append("balance: ").append(balance).append(",")
                .append("currency: ").append(currency).append(",")
                .append("active: ").append(active).append(",")
                .append("expDate: ").append(expDate).append(",")
                .append("linkedAccounts  count: ").append(linkedAccounts.size()).append(";");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Card && id.equals(((Card) o).getId());
    }

    public enum Status implements Serializable {

        UNKNOWN(-1), VALID(0), INVALID_PIN_LIMIT(1), FRAUD_BLOCK(10), TEMP_BLOCK(20), PERM_BLOCK(21), EXPIRED(99);

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

    public enum Type implements Serializable {

        DEBIT(1), CREDIT(2);

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

    public enum Active implements Serializable {

        YES(1), NO(0);

        private int value;

        Active(int value) {
            this.value = value;
        }

        @JsonCreator
        public static Active fromInt(int value) {
            for (Active type : Active.values())
                if (type.toInt() == value)
                    return type;
            return null;
        }

        @JsonValue
        public int toInt() {
            return value;
        }

    }

    public enum Category implements Serializable {

        REGULAR("regular"), VIRTUAL("virtual"), EXTERNAL("external");

        private String type;

        Category(String type) {
            this.type = type;
        }

        @JsonCreator
        public static Category fromString(String value) {
            for (Category type : Category.values())
                if (type.toString().equals(value))
                    return type;
            return null;
        }

        @JsonValue
        public String toString() {
            return type;
        }

    }
}
