package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Smolyaninov on 31.01.2017.
 */

public class AccountDetailRequest extends StampedRequest {

    private String id;

    public AccountDetailRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
