package com.bpc.modulesdk.rest.dto.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public class OperationStatus implements Serializable {
    private Type type;
    private String id;
    private boolean cancellable;
    private String timestamp;
    private String description;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Type implements Serializable {

        PROCESSING("PROCESSING"), COMPLETED("COMPLETED"), REJECTED("REJECTED"), CANCELLED("CANCELLED");

        private String type;

        Type(String type) {
            this.type = type;
        }

        @JsonCreator
        public static OperationStatus.Type fromString(String value) {
            for (OperationStatus.Type type : OperationStatus.Type.values())
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