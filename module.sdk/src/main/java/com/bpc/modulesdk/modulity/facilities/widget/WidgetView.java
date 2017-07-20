package com.bpc.modulesdk.modulity.facilities.widget;

/**
 * Created by dzmitrystrupinski on 6/6/17.
 */

public interface WidgetView<T, U> {
    void updateData(T info1, U info2);
}
