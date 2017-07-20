package com.bpc.modulesdk.ui.watchers;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.bpc.modulesdk.helpers.TextInputLayoutHelper;

/**
 * Created by Masloed on 02.09.2015.
 */
public class AmountErrorWatcher
        implements TextWatcher {
    private final TextInputLayout mTextInputLayout;

    private boolean isNotBeEmpty = false;

    public AmountErrorWatcher(TextInputLayout textInputLayout) {
        mTextInputLayout = textInputLayout;
        init();
    }


    private void init() {
        mTextInputLayout.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) TextInputLayoutHelper.setError(mTextInputLayout,TextInputLayoutHelper.InputType.AMOUNT);
            }
        });
    }


    @Override
    public void afterTextChanged(Editable s) {
        TextInputLayoutHelper.setError(mTextInputLayout,TextInputLayoutHelper.InputType.AMOUNT);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //
    }
}
