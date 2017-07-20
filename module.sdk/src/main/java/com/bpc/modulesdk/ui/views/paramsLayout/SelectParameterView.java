package com.bpc.modulesdk.ui.views.paramsLayout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bpc.modulesdk.fragment.dialogs.SelectDialogFragment;

import java.util.LinkedList;
import java.util.List;

import ru.bpc.mobilebanksdk.R;


/**
 * Created by Samoylov on 27.10.2015.
 */
public class SelectParameterView extends ParameterView implements DialogInterface.OnClickListener {

    private TextView viewValueCap;
    private TextView textValue;

    private int selectedPosition;

    public SelectParameterView(Context context, ParameterRecord parameter, boolean isEditable) {
        super(context, parameter, null, null, isEditable);
    }

    @Override
    protected void initEditMode() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_parameter_date, this);

        viewValueCap = (TextView) findViewById(R.id.view_value_cap);
        textValue = (TextView) findViewById(R.id.text_value);

        updateView(getSelectedPositionFromParameter());

        viewValueCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectDialog();
            }
        });
        textValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectDialog();
            }
        });
    }

    @Override
    protected void initNotEditMode() {
        selectedPosition = getSelectedPositionFromParameter();
        super.initNotEditMode();
    }

    private int getSelectedPositionFromParameter() {
        int pos = -1;
        if (getParameter().getValue() != null) {
            List<SelectionListEntry> selectItems = getParameter().getSelectionList();
            if (selectItems != null)
                for (int i = 0; i < selectItems.size(); i++)
                    if (getParameter().getValue().equals(selectItems.get(i).getValue())) {
                        pos = i;
                        break;
                    }
        }
        return pos;
    }

    @Override
    public String getEditedValue() {
        if (!isValidValue())
            return null;
        return getParameter().getSelectionList().get(selectedPosition).getValue();
    }

    @Override
    public String getUserRepresentationValue() {
        if (!isValidValue())
            return null;
        return getParameter().getSelectionList().get(selectedPosition).getLabel();
    }

    @Override
    public boolean isValidValue() {
        return selectedPosition >= 0;
    }

    private void openSelectDialog() {
        if (!(getContext() instanceof Activity) || getParameter().getSelectionList() == null || getParameter().getSelectionList().isEmpty())
            return;
        List<String> values = new LinkedList<>();
        for (SelectionListEntry entry : getParameter().getSelectionList())
            if (entry.getValue() != null)
                values.add(entry.getLabel() == null ? "" : entry.getLabel());

        SelectDialogFragment selectDialogFragment = new SelectDialogFragment();
        selectDialogFragment.setListener(this);
        selectDialogFragment.setValues(values);
        selectDialogFragment.setTitle(getParameter().getName());
        selectDialogFragment.show(((Activity) getContext()).getFragmentManager(), null);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        updateView(which);

        if (valueChangeListener != null)
            valueChangeListener.onChange(this);
    }

    private void updateView(int newSelectedPosition) {
        selectedPosition = newSelectedPosition;
        if (selectedPosition >= 0) {
            textValue.setVisibility(VISIBLE);
            viewValueCap.setVisibility(GONE);

            textValue.setText(getParameter().getSelectionList().get(selectedPosition).getLabel());
        } else {
            viewValueCap.setVisibility(VISIBLE);
            textValue.setVisibility(GONE);
        }
    }
}
