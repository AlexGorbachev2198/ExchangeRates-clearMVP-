package com.bpc.modulesdk.rest.dto.pojo;

import java.io.Serializable;

/**
 * Created by Samoylov on 20.01.2017.
 */

public enum PaymentSourceType implements Serializable {
    CARD(1), ACCOUNT(2), LINKED_ACCOUNT(2);

    private int code;

    PaymentSourceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentSourceType getByCode(int code) {
        for (PaymentSourceType pst : values()) {
            if (pst.getCode() == code) {
                return pst;
            }
        }
        return null;
    }

}
