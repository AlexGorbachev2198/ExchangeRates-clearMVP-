package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Samoylov on 25.01.2017.
 */

public class CardDetailRequest  extends  StampedRequest{

    private String id;

    public CardDetailRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
