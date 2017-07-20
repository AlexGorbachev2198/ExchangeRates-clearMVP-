package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;

import com.bpc.modulesdk.ui.watchers.CardExpirationDateTextWatcher;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Siarhei Mikevich on 3/31/17.
 */

public class CardExpDateEditText extends android.support.v7.widget.AppCompatEditText {

    private final int MAXIMUM_CARD_EXP_DATE_LENGTH = 5;
    private Pattern patternValidateMonth = Pattern.compile("0[1-9]|1[0-2]");

    public CardExpDateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CardExpDateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardExpDateEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setKeyListener(DigitsKeyListener.getInstance("0123456789/"));
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAXIMUM_CARD_EXP_DATE_LENGTH)});
        addTextChangedListener(new CardExpirationDateTextWatcher());
        setMaxLines(1);
    }

    public boolean isValidExpirationDate() {
        boolean isValidDate = false;
        String[] expirationDate = this.getText().toString().split("/");
        String month = expirationDate[0];
        String year = expirationDate.length == 2 ? expirationDate[1] : "0";
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if(Integer.parseInt(year) > currentYear && patternValidateMonth.matcher(month).matches()) {
            isValidDate = true;
        }
        else if(Integer.parseInt(year) == currentYear && patternValidateMonth.matcher(month).matches() && Integer.parseInt(month) >= currentMonth) {
            isValidDate = true;
        }
        return isValidDate;
    }

}
