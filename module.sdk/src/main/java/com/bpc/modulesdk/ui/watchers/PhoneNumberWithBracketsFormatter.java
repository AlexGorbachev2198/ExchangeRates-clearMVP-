package com.bpc.modulesdk.ui.watchers;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;

/**
 * Created by Samoylov on 02.11.2015.
 */
public class PhoneNumberWithBracketsFormatter extends PhoneNumberFormattingTextWatcher {

    /**
     * Максимальное количество цифр в Российском номере
     */
    public static int RUS_PHONE_LENGTH = 11;
    public static int ALL_PHONE_LENGTH = 15;
    private boolean isEdit = false;
    private int maxLength = -1;

    public PhoneNumberWithBracketsFormatter() {
        super();
    }

    public PhoneNumberWithBracketsFormatter(int maxLength) {
        super();
        this.maxLength = maxLength;
    }

    @Override
    public synchronized void afterTextChanged(Editable s) {
        //Ограничиваем длину только для русских номеров
        if (isEdit)
            return;
        boolean isNeedFilter = false;
        if (maxLength > 0) {
            if (maxLength > 0 && s.length() > maxLength) {
                isNeedFilter = isNeedFilter(s, maxLength);
            } else
                s.setFilters(new InputFilter[]{});
        } else {
            if (s.length() > RUS_PHONE_LENGTH && (s.charAt(0) == '8' || (s.charAt(0) == '+' && s.charAt(1) == '7'))) {
                isNeedFilter = isNeedFilter(s, RUS_PHONE_LENGTH);
            } else if (s.length() > ALL_PHONE_LENGTH) {
                isNeedFilter = isNeedFilter(s, ALL_PHONE_LENGTH);
            } else
                s.setFilters(new InputFilter[]{});
        }

        super.afterTextChanged(s);

        if (isNeedFilter)
            s.setFilters(new InputFilter[]{new InputFilter.LengthFilter(s.length())});

        //Заменяем пробелы на скобки
        int openPosition = -1;
        int closePosition = -1;
        boolean isFirst = true;
        for (int i = 0; i < s.length(); i++) {
            char chatAtI = s.charAt(i);
            if (chatAtI == '(' || chatAtI == ')')
                return;

            if (chatAtI == ' ') {
                if (isFirst) {
                    isFirst = false;
                    openPosition = i;
                } else {
                    closePosition = i;
                    break;
                }
            }
        }
        if (openPosition > 0 && closePosition > 0) {
            s.replace(openPosition, openPosition + 1, "(");
            s.replace(closePosition, closePosition + 1, ")");
        }

    }

    /**
     * Обрезает если количество цифр превыщшает или приблизилось к максимуму
     *
     * @param s           - строка для обрезки
     * @param maxNumCount - максимальное количество цифр
     * @return - true, если количество символов привысило или приблизилось к максимуму
     */
    private boolean isNeedFilter(Editable s, int maxNumCount) {
        int numCount = 0;
        int cutPosition = 0;
        for (int i = 0; i < s.length(); i++) {
            char chatAtI = s.charAt(i);
            if (chatAtI >= '0' && chatAtI <= '9')
                numCount++;
            if (numCount == maxNumCount)
                cutPosition = i + 1;
        }
        if (cutPosition > 0) {
            isEdit = true;
            s.replace(cutPosition, s.length(), "");
            isEdit = false;
            return true;
        }
        return false;
    }
}
