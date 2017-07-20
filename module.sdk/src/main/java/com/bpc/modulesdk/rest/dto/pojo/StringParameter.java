package com.bpc.modulesdk.rest.dto.pojo;

public class StringParameter extends CardOrAccountParameter {

    private String value = "";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return STRING;
    }

}