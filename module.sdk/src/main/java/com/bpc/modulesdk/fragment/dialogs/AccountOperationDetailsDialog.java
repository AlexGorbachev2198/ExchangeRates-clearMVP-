package com.bpc.modulesdk.fragment.dialogs;

import android.support.v4.content.ContextCompat;

import com.bpc.modulesdk.rest.dto.pojo.AccountOperationRecord;
import com.bpc.modulesdk.utils.CurrencyHelper;
import com.bpc.modulesdk.utils.DateHelper;

import java.text.DateFormat;
import java.util.Date;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 14.06.2017.
 */

public class AccountOperationDetailsDialog extends OperationDetailsDialog {

    @Override
    protected void fillContent() {
        AccountOperationRecord operation = (AccountOperationRecord) super.operation;
        String dateTimeString;

        Date operationDate = DateHelper.parseDateTime(operation.getTimestamp());
        dateTimeString = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(operationDate);
        int textColor;
        int currStyle;
        if (operation.getOperationAmount().getAmount().signum() < 0) {
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
                operation.getOperationAmount().getAmount(),
                operation.getOperationAmount().getCurrency(),
                currStyle,
                currStyle,
                true)
        );
        if (operation.getFeeAmount() != null) {
            boolean sign = operation.getFeeAmount().getAmount() != null && operation.getFeeAmount().getAmount().intValue() < 0;
            String text = getString(R.string.fee) + ": " + CurrencyHelper.formatAmount(
                    getActivity(),
                    operation.getFeeAmount().getAmount(),
                    operation.getFeeAmount().getCurrency(),
                    R.style.list_balance_value_fract,
                    R.style.list_balance_currency_name
                    , sign);
            fee.setText(text);
        }

        summ.setTextColor(textColor);
        description.setText(operation.getDescription());

        closeButton.setOnClickListener(v -> dismissAllowingStateLoss());
    }
}
