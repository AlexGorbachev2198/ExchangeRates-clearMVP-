package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;

/**
 * Created by Siarhei Mikevich on 3/24/17.
 */

public class CardPinEditText extends android.support.v7.widget.AppCompatEditText {

    public CardPinEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CardPinEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardPinEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
    }

}
