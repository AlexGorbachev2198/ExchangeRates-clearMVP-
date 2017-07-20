package com.bpc.modulesdk.ui.watchers;

import android.text.Editable;

public class CardExpirationDateTextWatcher extends AfterTextChangedTextWatcher {

    private boolean isEdit = false;
    private int lastLength = 0;

    @Override
    public void afterTextChanged(Editable s) {
        if (isEdit)
            return;
        if (lastLength > s.length()) {
            lastLength = s.length();
            return;
        }

        StringBuilder builder = new StringBuilder();
        char ch;
        for (int i = 0; builder.length() < 5 && s.length() > i; i++) {
            ch = s.charAt(i);
            if (ch >= '0' && ch <= '9')
                builder.append(ch);
            if (builder.length() == 2)
                builder.append('/');
        }
        isEdit = true;
        s.clear();
        s.append(builder.toString());
        isEdit = false;

        lastLength = s.length();
    }
}