package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/2/17.
 */

public class IdentityDocumentInfoEntry implements Serializable {
    private IdentityDocumentType type;
    private String number;
    List<String> images;

    public IdentityDocumentInfoEntry() {
    }

    public IdentityDocumentInfoEntry(IdentityDocumentType type, String number) {
        this.type = type;
        this.number = number;
    }

    public IdentityDocumentType getType() {
        return type;
    }

    public void setType(IdentityDocumentType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public enum IdentityDocumentType {
        ID("ID"), PASSPORT("PASSPORT");

        private String type;

        IdentityDocumentType(String type) {
            this.type = type;
        }

        public static IdentityDocumentType fromString(String value) {
            for (IdentityDocumentType type : IdentityDocumentType.values()) {
                String typeString = type.toString();
                if (typeString.equalsIgnoreCase(value.trim())) {
                    return type;
                }
            }

            return null;
        }

        public String toString() {
            return type;
        }
    }
}
