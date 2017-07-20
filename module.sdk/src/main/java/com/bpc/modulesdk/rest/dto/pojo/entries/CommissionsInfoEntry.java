package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Siarhei Mikevich on 6/1/17.
 */

public class CommissionsInfoEntry implements Serializable {

    private List<CommissionRecord> list;
    private MoneyEntry totalAmount;

    public List<CommissionRecord> getList() {
        return list;
    }

    public void setList(List<CommissionRecord> list) {
        this.list = list;
    }

    public MoneyEntry getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(MoneyEntry totalAmount) {
        this.totalAmount = totalAmount;
    }

}
