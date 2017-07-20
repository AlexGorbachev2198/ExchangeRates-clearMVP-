package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

public class SelectionListEntry implements Serializable {
    private String value;
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
