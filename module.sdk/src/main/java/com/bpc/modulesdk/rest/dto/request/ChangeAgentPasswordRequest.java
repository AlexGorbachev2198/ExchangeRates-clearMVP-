package com.bpc.modulesdk.rest.dto.request;

/**
 * Created by Smolyaninov on 06.06.2017.
 */

public class ChangeAgentPasswordRequest extends StampedRequest {

    private String oldpwd;
    private String newpwd;

    public ChangeAgentPasswordRequest(String oldpwd, String newpwd) {
        this.oldpwd = oldpwd;
        this.newpwd = newpwd;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }
}
