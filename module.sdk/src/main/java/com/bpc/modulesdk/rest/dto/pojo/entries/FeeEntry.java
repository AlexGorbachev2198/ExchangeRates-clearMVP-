package com.bpc.modulesdk.rest.dto.pojo.entries;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 3/20/17.
 */

public class FeeEntry implements Parcelable, Serializable {
    public MoneyEntry getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(MoneyEntry feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private MoneyEntry feeAmount;
    private String description;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.feeAmount, flags);
        dest.writeString(this.description);
    }

    public FeeEntry() {
    }

    protected FeeEntry(Parcel in) {
        this.feeAmount = in.readParcelable(MoneyEntry.class.getClassLoader());
        this.description = in.readString();
    }

    public static final Creator<FeeEntry> CREATOR = new Creator<FeeEntry>() {
        @Override
        public FeeEntry createFromParcel(Parcel source) {
            return new FeeEntry(source);
        }

        @Override
        public FeeEntry[] newArray(int size) {
            return new FeeEntry[size];
        }
    };
}
