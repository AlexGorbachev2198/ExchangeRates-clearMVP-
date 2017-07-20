package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;

public class CustomSwitch extends SwitchCompat implements ProgrammaticallyCheckable {
    private CompoundButton.OnCheckedChangeListener mListener = null;

    public CustomSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSwitch(Context context) {
        super(context);
    }

    @Override
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        this.mListener = listener;
        super.setOnCheckedChangeListener(listener);
    }

    @Override
    public void setCheckedProgrammatically(boolean checked) {
        super.setOnCheckedChangeListener(null);
        super.setChecked(checked);
        super.setOnCheckedChangeListener(mListener);
    }
}