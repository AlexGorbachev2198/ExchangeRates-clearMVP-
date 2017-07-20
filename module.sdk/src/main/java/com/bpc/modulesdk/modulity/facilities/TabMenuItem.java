package com.bpc.modulesdk.modulity.facilities;



import android.app.Fragment;

import com.bpc.modulesdk.modulity.facilities.mainmenu.MenuItem;

/**
 * Created by Samoylov on 26.11.2015.
 */
public class TabMenuItem<T extends Fragment> extends MenuItem {
    final Class<T> fragmentClass;
    final int activeImageId;

    public TabMenuItem(int titleId, int imageId, int activeImageId, Class<T> fragmentClass) {
        super(titleId, imageId);
        this.fragmentClass = fragmentClass;
        this.activeImageId = activeImageId;
    }

    public Class<T> getFragmentClass() {
        return fragmentClass;
    }

    public int getActiveImageId() {
        return activeImageId;
    }
}
