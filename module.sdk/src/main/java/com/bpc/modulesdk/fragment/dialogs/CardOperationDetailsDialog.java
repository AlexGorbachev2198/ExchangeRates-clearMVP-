package com.bpc.modulesdk.fragment.dialogs;

import android.support.v4.content.ContextCompat;

import com.bpc.modulesdk.rest.dto.pojo.CardOperationRecord;
import com.bpc.modulesdk.utils.CurrencyHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 14.06.2017.
 */

public class CardOperationDetailsDialog extends OperationDetailsDialog {
    @Override
    protected void fillContent() {

        CardOperationRecord operation = (CardOperationRecord) super.operation;
        String dateTimeString;
        try {
            Date operationDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).parse(operation.getTimestamp());
            dateTimeString = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(operationDate);
        } catch (ParseException e) {
            dateTimeString = operation.getTimestamp();
        }
        int textColor;
        int currStyle;
        if (operation.getAmount().signum() < 0) {
            textColor = ContextCompat.getColor(getActivity(), R.color.red);
            currStyle = R.style.operation_value_negative;
        } else {
            textColor = ContextCompat.getColor(getActivity(), R.color.green);
            currStyle = R.style.operation_value_positive;
        }
        dateTime.setText(dateTimeString);
        type.setText(operation.getType());
        summ.setText(CurrencyHelper.formatAmount(
                getActivity(),
                operation.getAmount(),
                operation.getCurrency(),
                currStyle,
                currStyle,
                true)
        );
        if (operation.getFeeAmount() != null) {
            String text = getString(R.string.fee) + ": " + CurrencyHelper.formatAmount(
                    getActivity(),
                    operation.getFeeAmount(),
                    operation.getFeeCurrency(),
                    R.style.list_balance_value_fract,
                    R.style.list_balance_currency_name
                    , operation.getFeeAmount().intValue() < 0);
            fee.setText(text);
        }

        summ.setTextColor(textColor);
        description.setText(operation.getDescription());

        closeButton.setOnClickListener(v -> dismissAllowingStateLoss());
    }
}
