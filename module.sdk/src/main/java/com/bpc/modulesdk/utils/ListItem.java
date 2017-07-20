package com.bpc.modulesdk.utils;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class ListItem<T> {

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_TITLE = 1;
    public static final int VIEW_TYPE_FOOTER = 2;
    public static final int VIEW_TYPE_HEADER = 3;
    public static final int VIEW_TYPE_EMPTY = 4;
    public static final int VIEW_TYPE_LAST = 5;

    private int viewType;
    private String title;
    private T item;

    /**
     * @param viewType {@link #VIEW_TYPE_FOOTER} or {@link #VIEW_TYPE_HEADER}
     */
    public ListItem(int viewType) {
        this.viewType = viewType;
    }

    public ListItem(String title) {
        this.viewType = VIEW_TYPE_TITLE;
        this.title = title;
    }

    public ListItem(T item) {
        this.viewType = VIEW_TYPE_ITEM;
        this.item = item;
    }

    public boolean isTitleItem() {
        return viewType == VIEW_TYPE_TITLE;
    }

    public String getTitle() {
        return title;
    }

    public T getItem() {
        return item;
    }

    public int getViewType() {
        return viewType;
    }
}
