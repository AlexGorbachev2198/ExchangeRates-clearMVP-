package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentProviderEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentProvidersResponse extends MainResponse {
    private List<PaymentProviderEntry> providers;

    public PaymentProvidersResponse() {
    }

    public List<PaymentProviderEntry> getProviders() {
        return providers;
    }

    public void setProviders(List<PaymentProviderEntry> providers) {
        this.providers = providers;
    }
}
