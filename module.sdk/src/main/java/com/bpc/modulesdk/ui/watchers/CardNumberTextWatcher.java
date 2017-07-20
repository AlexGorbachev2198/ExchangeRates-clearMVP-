package com.bpc.modulesdk.ui.watchers;

import android.text.Editable;

public class CardNumberTextWatcher extends AfterTextChangedTextWatcher {

    private static final char DIVIDER = ' ';
    private static final int GROUP_SIZE = 4;

    private boolean isEditing;

    @Override
    public void afterTextChanged(Editable s) {
        if (isEditing)
            return;
        isEditing = true;

        int pos = 0;
        //Added DIVIDER where it should be and remove where it shouldn't
        while (true) {
            if (pos >= s.length()) break;
            if (s.charAt(pos) == DIVIDER) {
                //Find DIVIDER. If it shouldn't be here - remove.
                if ((pos + 1) % (GROUP_SIZE + 1) != 0) {
                    s.delete(pos, pos + 1);
                    continue;
                }
            } else {
                //Don't find DIVIDER. If it should be here - add.
                if ((pos + 1) % (GROUP_SIZE + 1) == 0) {
                    s.insert(pos, "" + DIVIDER);
                    pos++;
                }
            }
            pos++;
        }


        isEditing = false;
    }

}
