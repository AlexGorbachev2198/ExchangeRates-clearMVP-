package com.bpc.modulesdk.rest.dto.pojo;

public class BooleanParameter extends CardOrAccountParameter {

    private boolean value=false;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return BOOLEAN;
    }
}