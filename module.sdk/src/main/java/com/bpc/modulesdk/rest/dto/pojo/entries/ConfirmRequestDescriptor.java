package com.bpc.modulesdk.rest.dto.pojo.entries;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * Created by Masloed on 25.11.2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmRequestDescriptor implements Serializable {

    private Type type;
    private String scardNum;
    private String scodeId;
    private String agreementInfo;

    public String getScardNum() {
        return scardNum;
    }

    public Type getType() {
        return type;
    }

    public void setScardNum(String scardNum) {
        this.scardNum = scardNum;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getScodeId() {
        return scodeId;
    }

    public void setScodeId(String scodeId) {
        this.scodeId = scodeId;
    }

    public String getAgreementInfo() {
        return agreementInfo;
    }

    public void setAgreementInfo(String agreementInfo) {
        this.agreementInfo = agreementInfo;
    }

    public enum Type implements Serializable {

        OTP("otp"), PASSWORD("password"), SCRATCHCODE("scratchcode"), AGREEMENT("agreement"), ONEPASS("onepass");

        private String type;

        Type(String type) {
            this.type = type;
        }

        @JsonCreator
        public static Type fromString(String value) {
            for (Type type : Type.values())
                if (type.toString().equals(value))
                    return type;
            return null;
        }

        @JsonValue
        public String toString() {
            return type;
        }

    }
}
