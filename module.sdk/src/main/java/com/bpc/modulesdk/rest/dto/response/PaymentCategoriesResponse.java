package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.PaymentCategoryEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentCategoriesResponse extends MainResponse {
    private List<PaymentCategoryEntry> categories;

    public PaymentCategoriesResponse() {
    }

    public List<PaymentCategoryEntry> getCategories() {
        return categories;
    }

    public void setCategories(List<PaymentCategoryEntry> categories) {
        this.categories = categories;
    }
}
