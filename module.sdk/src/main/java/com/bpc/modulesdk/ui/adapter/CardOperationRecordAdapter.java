package com.bpc.modulesdk.ui.adapter;

import com.bpc.modulesdk.rest.dto.pojo.CardOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;
import com.bpc.modulesdk.ui.widgets.OnRecyclerItemViewClickListener;
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

public class CardOperationRecordAdapter extends OperationRecordAdapter {
    @Override
    protected void fillViews(NormalViewHolder holder, OperationRecord operationRecord) {

        CardOperationRecord operation = (CardOperationRecord) operationRecord;

        String otString;
        try {
            Date operationDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).parse(operation.getTimestamp());
            otString = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(operationDate);

        } catch (ParseException e) {
            otString = operation.getTimestamp();
        }
        holder.summ.setText(CurrencyHelper.formatAmount(
                holder.summ.getContext(),
                operation.getAmount(),
                operation.getCurrency(),
                R.style.list_balance_value_fract,
                R.style.list_balance_currency_name,
                true
        ));

        if (operation.getFeeAmount() != null) {
            String fee = "- " + CurrencyHelper.formatAmount(
                    holder.summ.getContext(),
                    operation.getFeeAmount(),
                    operation.getFeeCurrency(),
                    R.style.list_balance_value_fract,
                    R.style.list_balance_currency_name
            );
            holder.fee.setText(fee);
        }

        holder.description.setText(operation.getDescription());
        holder.time.setText(otString);
        if (operation.getAmount().signum() < 0) {
            holder.icon.setImageResource(R.drawable.icon_statements_out);
        } else {
            holder.icon.setImageResource(R.drawable.icon_statements_in);
        }

        holder.itemView.setOnClickListener(new OnRecyclerItemViewClickListener<>(listener, operation));
    }
}
