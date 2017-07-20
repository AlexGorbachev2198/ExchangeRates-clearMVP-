package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Smolyaninov on 14.02.2017.
 */

public class ChangePasswordRequest extends StampedRequest {

    private String pwd;

    public ChangePasswordRequest(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
