package com.bpc.modulesdk.rest.dto.pojo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class CardDetail extends Card implements Serializable {
    public static final String STEREO_3DS = "card.securityFlags";
    public static final String STEREO_CREDIT = "card.credit";
    public static final String STEREO_USED_CREDIT = "card.usedCredit";
    public static final String STEREO_HOLD = "card.hold";
    public static final String STEREO_CONTRACT = "card.contract";
    public static final String STEREO_LEDGER_BALANCE = "card.ledgerBalance";

    private List<CardOrAccountParameter> details = Collections.emptyList();

    public List<CardOrAccountParameter> getDetails() {
        return details;
    }

    public void setDetails(List<CardOrAccountParameter> details) {
        this.details = details;
    }

    ///Stereo methods
    @JsonIgnore
    public boolean is3dsFlag() {
        BooleanParameter param = ((BooleanParameter) findParamByStereo(STEREO_3DS));
        return param != null && param.isValue();
    }

    @JsonIgnore
    public MoneyParameterObject getCredit() {
        MoneyParameter param = ((MoneyParameter) findParamByStereo(STEREO_CREDIT));
        return param != null ? param.getValue() : new MoneyParameterObject();
    }

    @JsonIgnore
    public MoneyParameterObject getUsedCredit() {
        MoneyParameter param = ((MoneyParameter) findParamByStereo(STEREO_USED_CREDIT));
        return param != null ? param.getValue() : new MoneyParameterObject();
    }

    @JsonIgnore
    public MoneyParameterObject getHold() {
        MoneyParameter param = ((MoneyParameter) findParamByStereo(STEREO_HOLD));
        return param != null ? param.getValue() : new MoneyParameterObject();
    }

    @JsonIgnore
    public String getContract() {
        StringParameter param = ((StringParameter) findParamByStereo(STEREO_CONTRACT));
        return param != null ? param.getValue() : "";
    }

    //===========Private methods======================================
    @JsonIgnore
    private int findIndexByStereo(@NonNull String stereo) {
        int index = -1;
        int size = details.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                CardOrAccountParameter param = details.get(i);
                if (stereo.equals(param.getStereotype())) return i;
            }
        }
        return index;
    }

    @JsonIgnore
    @Nullable
    private CardOrAccountParameter findParamByIndex(int index) {
        if (index != -1 && details.size() > index) return details.get(index);
        else return null;

    }

    @JsonIgnore
    @Nullable
    private CardOrAccountParameter findParamByStereo(String stereo) {
        int index = findIndexByStereo(stereo);
        return findParamByIndex(index);
    }

}