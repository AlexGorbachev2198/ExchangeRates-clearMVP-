package com.bpc.modulesdk.rest.dto.pojo.entries;

import java.io.Serializable;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

public class PaymentCategoryEntry implements Serializable {
    private String cid;
    private String name;
    private String image;

    public PaymentCategoryEntry() {
    }

    public PaymentCategoryEntry(String cid, String name, String image) {
        this.cid = cid;
        this.name = name;
        this.image = image;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
