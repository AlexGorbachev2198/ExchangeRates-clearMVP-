package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.entries.ErrorDescriptionEntry;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MainResponse extends CommonResponse {
    private ErrorDescriptionEntry errorDesc;
    private String description;

    public ErrorDescriptionEntry getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(ErrorDescriptionEntry errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public String getErrorUserMessage() {
        return errorDesc != null ? errorDesc.getUserMessage() : "";
    }
}
