package com.bpc.modulesdk.modulity.facilities.mainmenu;

import com.bpc.modulesdk.BaseApp;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 20.06.2017.
 */

public enum MainMenuGroup implements Comparable<MainMenuGroup> {

    AGENT(R.string.agent, 3), CUSTOMER(R.string.customer, 2), TOP(R.string.main, 100), BOTTOM(R.string.settings, 0);

    MainMenuGroup(int id, int priority) {
        this.group = BaseApp.getContext().getString(id);
        this.priority = priority;
    }

    String group;
    int priority;

    public String getGroup() {
        return group;
    }
    public int getPriority() {return  priority;}

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return group;
    }
}
