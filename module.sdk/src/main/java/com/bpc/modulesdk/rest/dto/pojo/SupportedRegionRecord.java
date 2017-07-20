package com.bpc.modulesdk.rest.dto.pojo;

/**
 * Created by Smolyaninov on 18.01.2017.
 */

public class SupportedRegionRecord {

    private String rid;
    private String name;
    private String description;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
