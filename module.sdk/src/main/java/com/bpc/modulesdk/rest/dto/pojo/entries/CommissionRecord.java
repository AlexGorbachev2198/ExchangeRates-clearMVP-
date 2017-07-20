package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by Siarhei Mikevich on 6/2/17.
 */

public class CommissionRecord implements Serializable {

    private String label;
    private MoneyEntry amount;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MoneyEntry getAmount() {
        return amount;
    }

    public void setAmount(MoneyEntry amount) {
        this.amount = amount;
    }

}
