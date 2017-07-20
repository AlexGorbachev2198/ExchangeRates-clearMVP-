package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.bpc.modulesdk.ui.watchers.PhoneNumberWithBracketsFormatter;

/**
 * Created by Samoylov on 05.11.2015.
 */
public class PhoneNumberEditText extends CustomEditText {

    private TextWatcher lastTextWatcher;

    public PhoneNumberEditText(Context context) {
        super(context);
        init();
    }

    public PhoneNumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhoneNumberEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Учитываются только цифры
     *
     * @param maxLength максимальное количество цыфр в номере телефона
     */
    public void setMaxLength(int maxLength) {
        if (lastTextWatcher != null)
            removeTextChangedListener(lastTextWatcher);

        addNewTextChangedListener(new PhoneNumberWithBracketsFormatter(maxLength));
    }

    protected void init() {
        if (isInEditMode())
            return;
        addNewTextChangedListener(new PhoneNumberWithBracketsFormatter());
    }

    private void addNewTextChangedListener(TextWatcher watcher) {
        lastTextWatcher = watcher;
        addTextChangedListener(watcher);
    }

    public Editable getNormalizeText() {
        String res = super.getText().toString();
        res = res.replaceAll("((?<!^)[^0-9]|^[^0-9+])", "");
        //Log.d("CustomFormattedEdit ", "NormalizeText is " + res);
        return new Editable.Factory().newEditable(res);
    }

    /**
     * For counting length using only numbers.
     *
     * @return number count
     */
    public int getLength() {
        return super.getText().toString().replaceAll("[^[\\d]]", "").length();
    }

    public boolean isValidPhoneNumber() {
        boolean isValidPhoneNumber = false;
        int length = this.getLength();
        String phoneNumber = this.getText().toString();
        if (length == PhoneNumberWithBracketsFormatter.RUS_PHONE_LENGTH && (phoneNumber.charAt(0) == '8' || (phoneNumber.charAt(0) == '+' && phoneNumber.charAt(1) == '7'))) {
            isValidPhoneNumber = true;
        } else if (length == PhoneNumberWithBracketsFormatter.ALL_PHONE_LENGTH) {
            isValidPhoneNumber = true;
        }
        // TODO: 13.04.2017 определиться с форматом номера, пока считаем любой номер корректным
        //return isValidPhoneNumber;
        return length > 0;
    }

}
