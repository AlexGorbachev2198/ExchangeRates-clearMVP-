package com.bpc.modulesdk.rest.dto.pojo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Smolyaninov on 24.01.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetail extends Account implements Serializable {
    private static final String STEREO_HOLD = "account.hold";
    private static final String STEREO_CONTRACT = "account.contract";
    private static final String STEREO_CREDIT = "account.credit";

    private List<CardOrAccountParameter> details = Collections.emptyList();

    public List<CardOrAccountParameter> getDetails() {
        return details;
    }

    public void setDetails(List<CardOrAccountParameter> details) {
        this.details = details;
    }

    //Stereo methods
    @JsonIgnore
    public MoneyParameterObject getHold() {
        MoneyParameter param = ((MoneyParameter) findParamByStereo(STEREO_HOLD));
        return param != null ? param.getValue() : new MoneyParameterObject();
    }

    @JsonIgnore
    @Nullable
    private CardOrAccountParameter findParamByStereo(String stereo) {
        int index = findIndexByStereo(stereo);
        return findParamByIndex(index);
    }

    @JsonIgnore
    @Nullable
    private CardOrAccountParameter findParamByIndex(int index) {
        if (index != -1 && details.size() > index) return details.get(index);
        else return null;

    }

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

    /*private List<CardOrAccountParameter> details = Collections.emptyList();

    public List<CardOrAccountParameter> getDetails() {
        return details;
    }

    public void setDetails(List<CardOrAccountParameter> details) {
        this.details = details;
    }



    @JsonIgnore
    public String getContract() {
        StringParameter param = ((StringParameter) findParamByStereo(STEREO_CONTRACT));
        return param != null ? param.getValue() : "";
    }

    @JsonIgnore
    public MoneyParameterObject getCredit() {
        MoneyParameter param = ((MoneyParameter) findParamByStereo(STEREO_CREDIT));
        return param != null ? param.getValue() : new MoneyParameterObject();
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
    }*/
}
