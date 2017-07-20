package com.bpc.modulesdk.rest.dto.pojo;

import java.math.BigDecimal;

/**
 * Created by Smolyaninov on 13.01.2017.
 */

public class RateInformation {

    private int order;
    private String name;
    private BigDecimal base;
    private BigDecimal buy;
    private BigDecimal sell;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }
}
