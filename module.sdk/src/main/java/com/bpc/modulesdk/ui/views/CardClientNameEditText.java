package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Siarhei Mikevich on 3/22/17.
 */

public class CardClientNameEditText extends EditText {

    public CardClientNameEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CardClientNameEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardClientNameEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        setInputType(InputType.TYPE_CLASS_TEXT);
    }

}
