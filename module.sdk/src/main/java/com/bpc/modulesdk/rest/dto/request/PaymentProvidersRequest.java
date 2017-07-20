package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentProvidersRequest extends StampedRequest {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> pids;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> cids;
    private String nameFilter;
    private LocationEntry location;

    public PaymentProvidersRequest() {
    }

    public PaymentProvidersRequest(List<String> pids, List<String> cids, String nameFilter, LocationEntry location) {
        this.pids = pids;
        this.cids = cids;
        this.nameFilter = nameFilter;
        this.location = location;
    }

    public List<String> getPids() {
        return pids;
    }

    public void setPids(List<String> pids) {
        this.pids = pids;
    }

    public List<String> getCids() {
        return cids;
    }

    public void setCids(List<String> cids) {
        this.cids = cids;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
