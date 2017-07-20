package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Smolyaninov on 27.01.2017.
 */

public class AccountChangeRequest {
    public String account;
    public String code;
    public String name;
    public Integer active;
    public Integer securityFlag;
    public Integer status;
    public Integer statusActiveDuration;

    public AccountChangeRequest(String account, String code, String name, Integer active, Integer securityFlag, Integer status, Integer statusActiveDuration) {

        this.account = account;
        this.code = code;
        this.name = name;
        this.active = active;
        this.securityFlag = securityFlag;
        this.status = status;
        this.statusActiveDuration = statusActiveDuration;
    }

    public AccountChangeRequest(String account, String name) {
        this.account = account;
        this.name = name;
    }
}
