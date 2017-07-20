package com.bpc.modulesdk.helpers;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;

import java.math.BigDecimal;
import com.bpc.modulesdk.ui.views.PhoneNumberEditText;
import com.bpc.modulesdk.ui.views.CardNumberEditText;
import ru.bpc.mobilebanksdk.R;

/**
 * Created by Masloed on 02.09.2015.
 */
public class TextInputLayoutHelper {

    private static final ForegroundColorSpan mNormalTextAppearance = new ForegroundColorSpan(Color.GRAY);
    private static final AlignmentSpan mAlignmentSpan = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE);


    public static boolean hasValidLength(TextInputLayout mTextInputLayout, int mMinLen, int mMaxLen) {
        final int length = length(mTextInputLayout);
        return hasValidLength(length, mMinLen, mMaxLen);
    }

    public static boolean hasValidLength(int length, int mMinLen, int mMaxLen) {
        if (isNotBeEmpty(mMinLen, mMaxLen)) return (length > 0);
        else if (mMaxLen == 0) return (length >= mMinLen);
        else return (length >= mMinLen && length <= mMaxLen);
    }

    public static boolean hasValidAmount(TextInputLayout mTextInputLayout) {
        if (mTextInputLayout == null || mTextInputLayout.getEditText() == null)
            return false;
        return hasValidAmount(mTextInputLayout.getEditText().getText().toString());
    }

    public static boolean hasValidAmount(String amount) {
        if (amount == null || amount.isEmpty()) return false;
        try {
            BigDecimal b = new BigDecimal(amount);
            return (b.compareTo(BigDecimal.ZERO) > 0);

        } catch (NumberFormatException nfe) {
            Log.d("AmountEditText", "NumberFormatException " + amount);
            return false;
        }
    }

    public static boolean isNotBeEmpty(int mMinLen, int mMaxLen) {
        return ((mMinLen == 0) && (mMaxLen == 0));
    }

    public static SpannableStringBuilder generateErrorText(TextInputLayout mTextInputLayout, int mMinLen, int mMaxLen) {
        SpannableStringBuilder mErrorText = new SpannableStringBuilder();
        final int length = length(mTextInputLayout);
        if (isNotBeEmpty(mMinLen, mMaxLen)) {
            if (length == 0)
                mErrorText.append(mTextInputLayout.getResources().getString(R.string.error_req_param));
        } else {

            if (length >= mMinLen && length <= mMaxLen) {//   x/mMax
                mErrorText.append(String.valueOf(length))
                        .append(" / ")
                        .append(String.valueOf(mMaxLen));
            } else if (length < mMinLen) {//   x/mMin
                mErrorText.append(String.valueOf(length))
                        .append(" / ")
                        .append(String.valueOf(mMinLen));
            }
        }
        return mErrorText;
    }

    public static void setError(TextInputLayout mTextInputLayout, int mMinLen, int mMaxLen) {
        if (mTextInputLayout == null || mTextInputLayout.getEditText() == null)
            return;
        mTextInputLayout.getEditText().setBackgroundResource(R.drawable.abc_edit_text_material);

        if (hasValidLength(mTextInputLayout, mMinLen, mMaxLen)) {
            mTextInputLayout.setError(null);
        } else {
            SpannableStringBuilder mErrorText = TextInputLayoutHelper.generateErrorText(mTextInputLayout, mMinLen, mMaxLen);
            mErrorText.setSpan(mAlignmentSpan, 0, mErrorText.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            mTextInputLayout.setError(mErrorText);
        }
    }

    public static void setError(TextInputLayout mTextInputLayout, InputType type) {
        if (mTextInputLayout == null || mTextInputLayout.getEditText() == null)
            return;
        if (type == InputType.AMOUNT) {
            mTextInputLayout.getEditText().setBackgroundResource(R.drawable.abc_edit_text_material);

            if (hasValidAmount(mTextInputLayout)) {
                mTextInputLayout.setError(null);
            } else {
                SpannableStringBuilder mErrorText = new SpannableStringBuilder().append(mTextInputLayout.getResources().getText(R.string.error_req_param));
                mErrorText.setSpan(mAlignmentSpan, 0, mErrorText.length(),
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                mTextInputLayout.setError(mErrorText);
            }
        }
    }

    public static void setError(TextInputLayout textInputLayout, String errorText) {
        if (textInputLayout == null || textInputLayout.getEditText() == null)
            return;

        //Для того чтобы обновить описание ошибки,  нужно почему -то всегда скинуть значение
        textInputLayout.setErrorEnabled(false);
        textInputLayout.setError(null);

        if (!TextUtils.isEmpty(errorText)) {
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder().append(errorText);
            stringBuilder.setSpan(mAlignmentSpan, 0, stringBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(stringBuilder);
        }
    }

    private static int length(TextInputLayout mTextInputLayout) {
        EditText editText = mTextInputLayout.getEditText();
        int length;

        if (editText instanceof PhoneNumberEditText)
            length = ((PhoneNumberEditText) editText).getLength();
        else if (editText instanceof CardNumberEditText)
            length = editText.getText().toString().replace(" ", "").length();
        else length = editText.getText().length();

        return length;
    }

    public enum InputType {
        AMOUNT
    }
}
