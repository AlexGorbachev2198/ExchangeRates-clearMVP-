package com.bpc.modulesdk.ui.watchers;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.bpc.modulesdk.BaseApp;

import ru.bpc.mobilebanksdk.R;


public  class EmptyFieldWatcher implements TextWatcher {

        public static final String ERROR_MESSAGE = BaseApp.getContext().getString(R.string.error_empty_field);

        private EditText editText;
        private TextInputLayout textInputLayout;

        public EmptyFieldWatcher(EditText editText, TextInputLayout textInputLayout) {
                this.editText = editText;
                this.textInputLayout = textInputLayout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty())textInputLayout.setError(ERROR_MESSAGE);
                else textInputLayout.setErrorEnabled(false);
        }
    }