package com.bpc.modulesdk.rest.dto.request;

import java.io.File;

/**
 * Created by dzmitrystrupinski on 5/12/17.
 */

public class ImageUploadRequest {
    private File imageFile;

    public ImageUploadRequest() {
    }

    public ImageUploadRequest(File imageFile) {
        this.imageFile = imageFile;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
}
