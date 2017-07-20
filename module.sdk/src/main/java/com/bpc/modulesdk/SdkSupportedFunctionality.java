package com.bpc.modulesdk;

import com.bpc.modulesdk.rest.dto.pojo.SupportedFunctionsRecord;

public class SdkSupportedFunctionality {

    private SupportedFunctionsRecord supportedFunctions = new SupportedFunctionsRecord();

    public SupportedFunctionsRecord getSupportedFunctions() {
        if (supportedFunctions == null) {
            supportedFunctions = new SupportedFunctionsRecord();
        }
        return supportedFunctions;
    }

    public void setSupportedFunctions(SupportedFunctionsRecord supportedFunctions) {
        this.supportedFunctions = supportedFunctions;
    }
}