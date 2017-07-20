package com.bpc.modulesdk.rest.dto.request;

import java.util.Date;

/**
 * Created by Smolyaninov on 31.01.2017.
 */

public class AccountHistoryRequest {
    public String id;
    public int page;
    public Date from;
    public Date to;

    public AccountHistoryRequest(String id, int page, Date from, Date to) {
        this.id = id;
        this.page = page;
        this.from = from;
        this.to = to;
    }
}
