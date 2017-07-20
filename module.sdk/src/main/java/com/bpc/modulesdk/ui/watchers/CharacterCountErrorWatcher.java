package com.bpc.modulesdk.ui.watchers;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;

import com.bpc.modulesdk.helpers.TextInputLayoutHelper;

/**
 * Created by Masloed on 02.09.2015.
 */
public class CharacterCountErrorWatcher extends AfterTextChangedTextWatcher {
    private final TextInputLayout mTextInputLayout;
    private int mMinLen;
    private int mMaxLen;

    public CharacterCountErrorWatcher(TextInputLayout textInputLayout, int minLen, int maxLen) {
        mTextInputLayout = textInputLayout;
        mMinLen = minLen;
        mMaxLen = maxLen;

        if (mTextInputLayout.getEditText() != null)
            mTextInputLayout.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus)
                    TextInputLayoutHelper.setError(mTextInputLayout, mMinLen, mMaxLen);
            });
    }

    @Override
    public void afterTextChanged(Editable s) {
        TextInputLayoutHelper.setError(mTextInputLayout, mMinLen, mMaxLen);
    }
}
