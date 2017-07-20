package com.bpc.modulesdk.ui.views.paramsLayout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ParameterRecord implements Serializable {

    private String id;
    private String name;
    private String description;
    private Type type;
    private int min;
    private int max;
    private String value;
    private List<SelectionListEntry> selectionList;
   /* private StorageOptionsEntry storageOpts = new StorageOptionsEntry();
    private MoneyOptionsEntry moneyOpts = new MoneyOptionsEntry();*/
    private String pattern;
    private Map<String, String> additionalParameters;
    private boolean mandatory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type != null ? type : Type.STRING;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<SelectionListEntry> getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(List<SelectionListEntry> selectionList) {
        this.selectionList = selectionList;
    }

  /*  public StorageOptionsEntry getStorageOpts() {
        return storageOpts;
    }

    public void setStorageOpts(StorageOptionsEntry storageOpts) {
        this.storageOpts = storageOpts;
    }

    public MoneyOptionsEntry getMoneyOpts() {
        return moneyOpts;
    }

    public void setMoneyOpts(MoneyOptionsEntry moneyOpts) {
        this.moneyOpts = moneyOpts;
    }*/

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Map<String, String> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(Map<String, String> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public enum Type implements Serializable {
        STRING("STRING"),
        DIGITS("DIGITS"),
        EMAIL("EMAIL"),
        PHONE("PHONE"),
        DATE("DATE"),
        SELECT("SELECT"),
        STORAGE("STORAGE"),
        MONEY("MONEY");

        private String type;

        Type(String type) {
            this.type = type;
        }

        @JsonCreator
        public static Type fromString(String value) {
            if (STRING.type.equals(value)) return STRING;
            if (DIGITS.type.equals(value)) return DIGITS;
            if (EMAIL.type.equals(value)) return EMAIL;
            if (PHONE.type.equals(value)) return PHONE;
            if (DATE.type.equals(value)) return DATE;
            if (SELECT.type.equals(value)) return SELECT;
            if (STORAGE.type.equals(value)) return STORAGE;
            if (MONEY.type.equals(value)) return MONEY;
            return STRING;
        }

        @JsonValue
        @Override
        public String toString() {
            return type;
        }
    }

}