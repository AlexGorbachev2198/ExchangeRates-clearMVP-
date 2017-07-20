package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Samoylov on 25.01.2017.
 */

public class CardChangeRequest {

    public String card;
    public String code;
    public String name;
    public Integer active;
    public Integer securityFlag;
    public Integer status;
    public Integer statusActiveDuration;

    public CardChangeRequest(String card, String code, String name, Integer active, Integer securityFlag, Integer status, Integer statusActiveDuration) {

        this.card = card;
        this.code = code;
        this.name = name;
        this.active = active;
        this.securityFlag = securityFlag;
        this.status = status;
        this.statusActiveDuration = statusActiveDuration;
    }

    public CardChangeRequest(String card, String name) {
        this.card = card;
        this.name = name;
    }
}
