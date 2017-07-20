package com.bpc.modulesdk.rest.dto.pojo;

import java.io.Serializable;
import java.util.Map;

public class OperationRecord implements Serializable {

    private static final long serialVersionUID = 407190158691371703L;
    private String timestamp;                                    //  "timestamp":"20170601132904"   -->   "timestamp":"2016-05-01T10:00:00.500Z"
    private String tid;
    private String type;
    private String description;
    private Map<String, String> additionalParameters;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(Map<String, String> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }
}