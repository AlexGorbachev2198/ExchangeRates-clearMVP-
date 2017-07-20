package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Samoylov on 25.01.2017.
 */

public class CardHistoryRequest {
    public String card;
    public int page;
    public String startDate;
    public String endDate;

    public CardHistoryRequest(String card, int page, String startDate, String endDate) {
        this.card = card;
        this.page = page;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
