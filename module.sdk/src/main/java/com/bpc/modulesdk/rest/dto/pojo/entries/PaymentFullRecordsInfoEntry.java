package com.bpc.modulesdk.rest.dto.pojo.entries;

import com.bpc.modulesdk.ui.views.paramsLayout.ParameterRecord;

import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/31/17.
 */

public class PaymentFullRecordsInfoEntry extends PaymentBaseInfoEntry {
    private List<ParameterRecord> params;

    public List<ParameterRecord> getParams() {
        return params;
    }

    public void setParams(List<ParameterRecord> params) {
        this.params = params;
    }
}
