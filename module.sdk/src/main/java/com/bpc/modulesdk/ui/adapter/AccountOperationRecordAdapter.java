package com.bpc.modulesdk.ui.adapter;

import com.bpc.modulesdk.rest.dto.pojo.AccountOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;
import com.bpc.modulesdk.ui.widgets.OnRecyclerItemViewClickListener;
import com.bpc.modulesdk.utils.CurrencyHelper;
import com.bpc.modulesdk.utils.DateHelper;

import java.text.DateFormat;
import java.util.Date;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 14.06.2017.
 */

public class AccountOperationRecordAdapter extends OperationRecordAdapter {

    @Override
    protected void fillViews(NormalViewHolder holder, OperationRecord operationRecord) {
        AccountOperationRecord operation = (AccountOperationRecord) operationRecord;
        Date operationDate = DateHelper.parseDateTime(operation.getTimestamp());
        String otString = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(operationDate);

        holder.summ.setText(CurrencyHelper.formatAmount(
                holder.summ.getContext(),
                operation.getOperationAmount().getAmount(),
                operation.getOperationAmount().getCurrency(),
                R.style.list_balance_value_fract,
                R.style.list_balance_currency_name,
                true
        ));

        if (operation.getFeeAmount() != null) {
            String fee = "- " + CurrencyHelper.formatAmount(
                    holder.summ.getContext(),
                    operation.getFeeAmount().getAmount(),
                    operation.getFeeAmount().getCurrency(),
                    R.style.list_balance_value_fract,
                    R.style.list_balance_currency_name
            );
            holder.fee.setText(fee);
        }

        holder.description.setText(operation.getDescription());
        holder.time.setText(otString);
        if (operation.getOperationAmount().getAmount().signum() < 0) {
            holder.icon.setImageResource(R.drawable.icon_statements_out);
        } else {
            holder.icon.setImageResource(R.drawable.icon_statements_in);
        }

        holder.itemView.setOnClickListener(new OnRecyclerItemViewClickListener<>(listener, operation));
    }
}
