package com.bpc.modulesdk.modulity.module;

import android.content.Context;

import com.bpc.modulesdk.modulity.facilities.WidgetType;
import com.bpc.modulesdk.modulity.facilities.widget.WidgetView;
import com.bpc.modulesdk.modulity.facilities.widget.WidgetViewListener;

/**
 * Created by dzmitrystrupinski on 6/5/17.
 */

public interface WidgetUser {
    /**
     * widget type to be checked when dealing with container
     * @return WidgetType
     */
    WidgetType getWidgetType();

    /**
     *
     * returns widget view. Widget callbacks are fired using listener
     * @param context
     * @param listener
     */

    WidgetView getWidgetView(Context context, WidgetViewListener listener);
}
