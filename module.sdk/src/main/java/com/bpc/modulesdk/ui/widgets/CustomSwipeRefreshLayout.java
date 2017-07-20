package com.bpc.modulesdk.ui.widgets;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Samoylov on 19.01.2017.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setColorSchemeResources(R.color.swipe_refresh_color_scheme1, R.color.swipe_refresh_color_scheme2,
                R.color.swipe_refresh_color_scheme3, R.color.swipe_refresh_color_scheme4);
        setEnabled(false);
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {
        super.setOnRefreshListener(listener);
        setEnabled(listener != null);
    }
}
