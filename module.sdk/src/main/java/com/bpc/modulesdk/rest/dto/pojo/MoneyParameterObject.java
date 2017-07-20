package com.bpc.modulesdk.rest.dto.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MoneyParameterObject implements Serializable {

    private BigDecimal amount = BigDecimal.valueOf(0);
    private String currency = "";

    public BigDecimal getAmount() {
        return amount != null ? amount : BigDecimal.valueOf(0);
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
}