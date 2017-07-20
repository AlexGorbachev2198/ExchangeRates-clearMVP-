package com.bpc.modulesdk.rest.dto.pojo.entries;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Masloed on 13.01.2017.
 */

public class MoneyEntry implements Serializable, Parcelable{

    private BigDecimal amount;
    private String currency;


    public MoneyEntry() {
    }

    @JsonIgnore
    public MoneyEntry(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(String.valueOf(amount));
        dest.writeString(currency);
    }

    private MoneyEntry(Parcel in) {
        Double amountValue = Double.valueOf(in.readString());
        amount = BigDecimal.valueOf(amountValue);
        currency = in.readString();
    }

    public static final Parcelable.Creator<MoneyEntry> CREATOR
            = new Parcelable.Creator<MoneyEntry>() {

        @Override
        public MoneyEntry createFromParcel(Parcel in) {
            return new MoneyEntry(in);
        }

        @Override
        public MoneyEntry[] newArray(int size) {
            return new MoneyEntry[size];
        }
    };
}


