package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

import com.bpc.modulesdk.ui.watchers.CardNumberTextWatcher;

import ru.bpc.mobilebanksdk.R;

public class CardNumberEditText extends EditText {
    //13-19)
    private static final int MINIMUM_CARD_NUMBER_LENGTH = 13;
    private static final int MAXIMUM_CARD_NUMBER_LENGTH = 19;

    private int mode;
    private final int CARD_NUMBER_MODE = 0;
    private final int ACCOUNT_NUMBER_MODE = 1;

    public CardNumberEditText(Context context) {
        super(context);
        init();
    }

    public CardNumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public CardNumberEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CardNumberEditText, 0, 0);
        try {
            mode = a.getInt(R.styleable.CardNumberEditText_card_number_edit_text_mode, CARD_NUMBER_MODE);
        } finally {
            a.recycle();
        }
    }

    private void init() {
        setupMode();
        addTextChangedListener(new CardNumberTextWatcher());
        setMaxLines(1);
    }

    private void setupMode() {
        switch (mode) {
            case CARD_NUMBER_MODE:
                setupCardNumberMode();
                break;
            case ACCOUNT_NUMBER_MODE:
                setupAccountNumberMode();
                break;
        }
    }

    private void setupCardNumberMode() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setKeyListener(DigitsKeyListener.getInstance("0123456789 "));
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAXIMUM_CARD_NUMBER_LENGTH)});
    }

    private void setupAccountNumberMode() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(MAXIMUM_CARD_NUMBER_LENGTH)});
    }

    public boolean isValidCardNumber() {
        String cardNumber = this.getText().toString();
        return cardNumber.length() <= MAXIMUM_CARD_NUMBER_LENGTH && cardNumber.length() >= MINIMUM_CARD_NUMBER_LENGTH;
    }

}