package com.bpc.modulesdk.fragment.dialogs;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.AccountOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.CardOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;
import com.bpc.modulesdk.utils.ExtraKeys;

import ru.bpc.mobilebanksdk.R;

public abstract class OperationDetailsDialog extends DialogFragment {

    private static final String EXTRA_KEY_OPERATION = "operation";

    protected TextView description;
    protected TextView summ;
    protected TextView fee;
    protected TextView dateTime;
    protected TextView type;
    protected ImageView closeButton;
    private LinearLayout menuContainer;

    protected OperationRecord operation;
    private String moneyResourceID;

    public static OperationDetailsDialog newInstance(String moneyResourceID, OperationRecord record) {
        OperationDetailsDialog dialog = null;
        if (record instanceof AccountOperationRecord) {
            dialog = new AccountOperationDetailsDialog();
        } else if (record instanceof CardOperationRecord) {
            dialog = new CardOperationDetailsDialog();
        }
        Bundle args = new Bundle();
        args.putString(ExtraKeys.ID, moneyResourceID);
        args.putSerializable(EXTRA_KEY_OPERATION, record);
        if (dialog != null) dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            operation = (OperationRecord) args.getSerializable(EXTRA_KEY_OPERATION);
            moneyResourceID = args.getString(ExtraKeys.ID);
        }
        if (operation == null)
            throw new IllegalArgumentException("Operation Record can't be null");

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        View v = inflater.inflate(R.layout.dialog_operation_details, null);
        bindViews(v);
        fillContent();
        return v;
    }

    protected abstract void fillContent();

    private void bindViews(View root) {
        closeButton = (ImageView) root.findViewById(R.id.close_button);
        dateTime = (TextView) root.findViewById(R.id.operation_datetime);
        summ = (TextView) root.findViewById(R.id.operation_summ);
        fee = (TextView) root.findViewById(R.id.operation_fee);
        description = (TextView) root.findViewById(R.id.operation_description);
        type = (TextView) root.findViewById(R.id.operation_type);
        menuContainer = (LinearLayout) root.findViewById(R.id.menu_container);
    }

}
