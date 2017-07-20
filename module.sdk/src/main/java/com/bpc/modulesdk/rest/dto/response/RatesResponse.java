package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.response.MainResponse;
import com.bpc.modulesdk.rest.dto.pojo.RateInformation;

import java.util.List;

/**
 * Created by Smolyaninov on 13.01.2017.
 */

public class RatesResponse extends MainResponse {

    private List<RateInformation> rates;

    public List<RateInformation> getRates() {
        return rates;
    }

    public void setRates(List<RateInformation> rates) {
        this.rates = rates;
    }
}
