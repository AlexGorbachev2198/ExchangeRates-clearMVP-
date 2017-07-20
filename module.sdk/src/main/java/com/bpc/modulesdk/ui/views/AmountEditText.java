package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Handler;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 31.03.2017.
 */

public class AmountEditText extends CustomEditText {

    private final int MAX_LENGTH = 17;

    private boolean round = false;

    public AmountEditText(Context context) {
        super(context);
        init();
    }

    public AmountEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttr(context, attrs);
    }

    public AmountEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        initAttr(context, attrs);
    }


    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AmmountEditText,
                0, 0);
        try {
            setRound(a.getBoolean(R.styleable.AmmountEditText_round, false));
        } finally {
            a.recycle();
        }
    }

    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
        //setFilters(new InputFilter[]{new AmmountDigitsInputFilter()});

        setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE) {

                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                Handler handler = new Handler();
                handler.postDelayed(this::clearFocus, 500);
            }
            return false; // pass on to other listeners.
        });
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH)});
    }

    public void setRound(boolean isRound){
        round = isRound;
    }

    private void formatEditText() {
        if (!round) {
            String temp = getText().toString();
            if (temp.contains(".")) {
                if (temp.charAt(temp.length() - 1) == '.') {
                    setText(temp + "00");
                } else if (temp.charAt(temp.length() - 2) == '.') {
                    setText(temp + "0");
                } else setText(temp.substring(0, temp.indexOf(".") + 3));
            } else {
                if (temp.equals("")) {
                    setText("0.00");
                } else setText(temp + ".00");
            }
        }
        clearFocus();

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (!focused) formatEditText();
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }


}
