package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 4/3/17.
 */

public class ErrorDescriptionEntry implements Serializable {

    private String userMessage;
    private int subcode;

    public int getSubcode() {
        return subcode;
    }

    public void setSubcode(int subcode) {
        this.subcode = subcode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
