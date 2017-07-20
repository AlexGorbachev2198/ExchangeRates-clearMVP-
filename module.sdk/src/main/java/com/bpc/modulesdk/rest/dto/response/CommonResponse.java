package com.bpc.modulesdk.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by Masloed on 24.11.2016.
 */
public abstract class CommonResponse implements Serializable {

    private int result = 0;

    public int getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return result == 0;
    }
}
