package com.bpc.modulesdk.ui.views.paramsLayout;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.bpc.modulesdk.ui.watchers.AfterTextChangedTextWatcher;

import ru.bpc.mobilebanksdk.R;


/**
 * Created by Samoylov on 27.10.2015.
 */
public class EditTextParameterView extends ParameterView {

    private TextInputLayout inputValue;
    private EditText editValue;

    public EditTextParameterView(Context context, ParameterRecord parameter, boolean isEditable) {
        super(context, parameter, null, null, isEditable);
    }

    @Override
    protected void initEditMode() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_parameter_edittext, this);
        inputValue = (TextInputLayout) findViewById(R.id.input_value);
        editValue = (EditText) findViewById(R.id.edit_value);

        inputValue.setHint(getParameter().getDescription());
        //editValue.addTextChangedListener(new CharacterCountErrorWatcher(inputValue, getParameter().getMin(), getParameter().getMax()));
        editValue.addTextChangedListener(new AfterTextChangedTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (valueChangeListener != null)
                    valueChangeListener.onChange(EditTextParameterView.this);
            }
        });
        if (getParameter().getValue() != null && !getParameter().getValue().isEmpty())
            editValue.setText(getParameter().getValue());
        if (getParameter().getMax() != 0)
            editValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(getParameter().getMax())});
        setInputType(editValue, getParameter().getType());
    }

    @Override
    public String getEditedValue() {
        if(editValue == null)
            return getParameter().getValue();
        return editValue.getText().toString();
    }

    @Override
    public String getUserRepresentationValue() {
        if(editValue == null)
            return getParameter().getValue();
        return editValue.getText().toString();
    }

    @Override
    public boolean isValidValue() {
        //return TextInputLayoutHelper.hasValidLength(inputValue, getParameter().getMin(), getParameter().getMax());
        return !TextUtils.isEmpty(editValue.getText().toString());
    }

    private void setInputType(EditText editText, ParameterRecord.Type type) {
        if (ParameterRecord.Type.DIGITS == type) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (ParameterRecord.Type.EMAIL == type) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }
    }
}
