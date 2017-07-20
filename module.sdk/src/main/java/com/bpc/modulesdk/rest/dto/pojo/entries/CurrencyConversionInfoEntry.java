package com.bpc.modulesdk.rest.dto.pojo.entries;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 3/20/17.
 */

public class CurrencyConversionInfoEntry implements Parcelable, Serializable {
    private MoneyEntry sourceFunds;
    private MoneyEntry targetFunds;
    private float forwardRate;
    private float backwardRate;

    public CurrencyConversionInfoEntry() {}

    protected CurrencyConversionInfoEntry(Parcel in) {
        sourceFunds = in.readParcelable(MoneyEntry.class.getClassLoader());
        targetFunds = in.readParcelable(MoneyEntry.class.getClassLoader());
        forwardRate = in.readFloat();
        backwardRate = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(sourceFunds, flags);
        dest.writeParcelable(targetFunds, flags);
        dest.writeFloat(forwardRate);
        dest.writeFloat(backwardRate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CurrencyConversionInfoEntry> CREATOR = new Creator<CurrencyConversionInfoEntry>() {
        @Override
        public CurrencyConversionInfoEntry createFromParcel(Parcel in) {
            return new CurrencyConversionInfoEntry(in);
        }

        @Override
        public CurrencyConversionInfoEntry[] newArray(int size) {
            return new CurrencyConversionInfoEntry[size];
        }
    };

    public MoneyEntry getSourceFunds() {
        return sourceFunds;
    }

    public void setSourceFunds(MoneyEntry sourceFunds) {
        this.sourceFunds = sourceFunds;
    }

    public MoneyEntry getTargetFunds() {
        return targetFunds;
    }

    public void setTargetFunds(MoneyEntry targetFunds) {
        this.targetFunds = targetFunds;
    }

    public float getForwardRate() {
        return forwardRate;
    }

    public void setForwardRate(float forwardRate) {
        this.forwardRate = forwardRate;
    }

    public float getBackwardRate() {
        return backwardRate;
    }

    public void setBackwardRate(float backwardRate) {
        this.backwardRate = backwardRate;
    }
}
