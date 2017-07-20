package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by Siarhei Mikevich on 6/1/17.
 */

public class TransactionsInfoEntry implements Serializable {

    private TransactionTypesCount segregation;
    private Integer totalNumber;

    public TransactionTypesCount getSegregation() {
        return segregation;
    }

    public void setSegregation(TransactionTypesCount segregation) {
        this.segregation = segregation;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

}
