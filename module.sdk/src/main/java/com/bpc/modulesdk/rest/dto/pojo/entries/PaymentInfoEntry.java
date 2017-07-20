package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.util.Map;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

public class PaymentInfoEntry extends PaymentBaseInfoEntry {
    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
