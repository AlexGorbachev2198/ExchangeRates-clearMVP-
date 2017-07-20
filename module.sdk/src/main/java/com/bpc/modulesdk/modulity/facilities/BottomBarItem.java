package com.bpc.modulesdk.modulity.facilities;

import com.bpc.modulesdk.modulity.facilities.mainmenu.MenuItem;

/**
 * Created by Smolyaninov on 22.06.2017.
 */

public class BottomBarItem extends MenuItem{

    private Class activityClass;

    public BottomBarItem(int titleId, int imageId, Class activityClass) {
        super(titleId, imageId);
        this.activityClass = activityClass;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }
}
