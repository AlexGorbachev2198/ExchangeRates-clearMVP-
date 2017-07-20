package com.bpc.modulesdk.modulity.facilities.mainmenu;

/**
 * Created by Samoylov on 26.11.2015.
 */
public class MenuItem {

    final private int titleId;
    final private int imageId;

    public MenuItem(int titleId, int imageId) {

        this.titleId = titleId;
        this.imageId = imageId;
    }

    public int getTitleId() {
        return titleId;
    }

    public int getImageId() {
        return imageId;
    }
}
