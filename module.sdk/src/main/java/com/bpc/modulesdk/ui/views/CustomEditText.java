package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 24.01.2017.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    protected String _customFont = null;
    protected int _style;
    protected boolean noEnter = false;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomAttributes(attrs, 0);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomAttributes(attrs, defStyle);
    }

    /**
     * Set custom attributes, particularly we're setting our font
     *
     * @param attrs
     */
    protected void setCustomAttributes(AttributeSet attrs, int defStyle) {
        if (isInEditMode())
            return;
        /*TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFont);
        _customFont = a.getString(R.styleable.CustomFont_font);
		if (_customFont != null) {
			if (!isInEditMode()) {
				CustomFontManager fontManager = CustomFontManager.getInstance();
				super.setTypeface(fontManager.getFont(getContext().getAssets(), _customFont), _style);
			}
		}
		a.recycle();*/
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomEditText,
                0, defStyle);
        try {
            noEnter = false;

        } finally {
            a.recycle();
        }
    }

    /**
     * If the user set a style assign it to our _style field so it can be applied to our custom font. Calling super
     * here allows you to use CustomTextView even if you're not changing the font.
     */
//    public void setTypeface(Typeface tf, int style) {
//        this._style = style;
//        super.setTypeface(tf, style);
//    }
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        InputConnection connection = super.onCreateInputConnection(outAttrs);
        int imeActions = outAttrs.imeOptions & EditorInfo.IME_MASK_ACTION;
        if (noEnter && (imeActions & EditorInfo.IME_ACTION_DONE) != 0) {
            // clear the existing action
            outAttrs.imeOptions ^= imeActions;
            // set the DONE action
            outAttrs.imeOptions |= EditorInfo.IME_ACTION_DONE;
        }
        if ((outAttrs.imeOptions & EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0) {
            outAttrs.imeOptions &= ~EditorInfo.IME_FLAG_NO_ENTER_ACTION;
        }
        return connection;
    }
}
