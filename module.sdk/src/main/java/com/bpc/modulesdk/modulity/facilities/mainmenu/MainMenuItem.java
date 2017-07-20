package com.bpc.modulesdk.modulity.facilities.mainmenu;

import android.app.Activity;
import android.support.annotation.NonNull;


public class MainMenuItem<T extends Activity> extends MenuItem implements Comparable<MainMenuItem> {

    private Class<T> activityClass;
    private MainMenuGroup group = MainMenuGroup.BOTTOM;

    public MainMenuItem(int titleId, int imageId, MainMenuGroup group, Class<T> activityClass) {
        this(titleId, imageId, activityClass);
        this.group = group;
    }

    public MainMenuItem(int titleId, int imageId, Class<T> activityClass) {
        super(titleId, imageId);
        this.activityClass = activityClass;
    }

    public Class<T> getActivityClass() {
        return activityClass;
    }

    public MainMenuGroup getGroup() {
        return group;
    }

    @Override
    public int compareTo(@NonNull MainMenuItem o) {
        return o.getGroup().getPriority() - this.getGroup().getPriority();
    }

}
