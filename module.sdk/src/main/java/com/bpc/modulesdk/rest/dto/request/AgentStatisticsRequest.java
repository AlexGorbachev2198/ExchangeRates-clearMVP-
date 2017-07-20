package com.bpc.modulesdk.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Siarhei Mikevich on 6/7/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentStatisticsRequest {
    private String from;
    private String to;
    private boolean details;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isDetails() {
        return details;
    }

    public void setDetails(boolean details) {
        this.details = details;
    }

    public AgentStatisticsRequest(String from, String to, boolean details) {
        this.from = from;
        this.to = to;
        this.details = details;
    }

}
