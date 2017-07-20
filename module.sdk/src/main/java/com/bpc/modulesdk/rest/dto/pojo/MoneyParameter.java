package com.bpc.modulesdk.rest.dto.pojo;

public class MoneyParameter extends CardOrAccountParameter {

    private MoneyParameterObject value = new MoneyParameterObject();

    public MoneyParameterObject getValue() {
        return value == null ? new MoneyParameterObject() : value;
    }

    public void setValue(MoneyParameterObject value) {
        this.value = value;
    }

    public String getType() {
        return MONEY;
    }

}