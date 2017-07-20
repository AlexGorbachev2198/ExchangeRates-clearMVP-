package com.bpc.modulesdk.rest.dto.response;

/**
 * Created by dzmitrystrupinski on 5/12/17.
 */

public class ImageUploadResponse extends MainResponse {
    private String imgId;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
