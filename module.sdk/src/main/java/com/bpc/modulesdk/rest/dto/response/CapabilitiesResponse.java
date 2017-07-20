package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.SupportedCurrencyRecord;
import com.bpc.modulesdk.rest.dto.pojo.SupportedFunctionsRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smolyaninov on 18.01.2017.
 */

public class CapabilitiesResponse extends MainResponse {

    private List<SupportedCurrencyRecord> supportedCurrencies = new ArrayList<>();
    private SupportedFunctionsRecord supportedFunctions;

    public List<SupportedCurrencyRecord> getSupportedCurrencies() {
        if (supportedCurrencies == null || supportedCurrencies.size() == 0) {
            supportedCurrencies = getDefaultCurrencies();
        }
        return supportedCurrencies;
    }

    public void setSupportedCurrencies(List<SupportedCurrencyRecord> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;

    }

    public SupportedFunctionsRecord getSupportedFunctions() {
        return supportedFunctions;
    }

    public void setSupportedFunctions(SupportedFunctionsRecord supportedFunctions) {
        this.supportedFunctions = supportedFunctions;
    }

    // TODO: 14.06.2017 move to setting configuration
    private static List<SupportedCurrencyRecord> getDefaultCurrencies() {
        List<SupportedCurrencyRecord> currencies = new ArrayList<>();
        SupportedCurrencyRecord rubRecord = SupportedCurrencyRecord.newInstanceRub();
        SupportedCurrencyRecord eurRecord = SupportedCurrencyRecord.newInstanceEur();
        SupportedCurrencyRecord usdRecord = SupportedCurrencyRecord.newInstanceUsd();
        currencies.add(rubRecord);
        currencies.add(eurRecord);
        currencies.add(usdRecord);
        return currencies;

    }
}
