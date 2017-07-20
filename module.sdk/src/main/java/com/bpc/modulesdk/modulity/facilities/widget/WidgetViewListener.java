package com.bpc.modulesdk.modulity.facilities.widget;

import com.bpc.modulesdk.modulity.facilities.WidgetType;

import java.util.Date;

/**
 * Created by dzmitrystrupinski on 6/5/17.
 */

public interface WidgetViewListener {
    void reloadData(WidgetType widgetType, Date startDate, Date endDate);
}
