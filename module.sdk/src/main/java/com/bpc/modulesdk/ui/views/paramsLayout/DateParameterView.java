package com.bpc.modulesdk.ui.views.paramsLayout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bpc.modulesdk.fragment.dialogs.DatePickerDialogFragment;
import com.bpc.modulesdk.fragment.dialogs.TimePickerDialogFragment;
import com.bpc.modulesdk.utils.DateHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Siarhei Mikevich on 5/24/17.
 */

public class DateParameterView extends ParameterView implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private final String TAG = "DateParameter";
    private final String FORMAT_DATE_FOR_USER = "dd MMMM yyyy";
    private final String FORMAT_DATE_TIME_FOR_USER = "dd MMMM yyyy, HH:mm";
    private final String FORMAT_DATE_TIME_DEFAULT = "EEE MMM dd HH:mm:ss zzz yyyy";

    private TextView viewDateCap;
    private TextView textDate;

    private Date value;
    private boolean isContainTime;
    Calendar selectedDateCalendar;
    TimePickerDialogFragment timePickerDialogFragment;

    public DateParameterView(Context context, ParameterRecord parameter, boolean isEditable) {
        super(context, parameter, null, null, isEditable);
    }

    @Override
    protected void initEditMode() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_parameter_date, this);

        viewDateCap = (TextView) findViewById(R.id.view_value_cap);
        textDate = (TextView) findViewById(R.id.text_value);

        isContainTime = DateHelper.isContainTime(getParameter().getPattern());
        setValue(parseValueFromParameter());

        viewDateCap.setOnClickListener(v -> openDatePickerDialog());
        textDate.setOnClickListener(v -> openDatePickerDialog());
    }

    @Override
    protected void initNotEditMode() {
        value = parseValueFromParameter();
        super.initNotEditMode();
    }

    private Date parseValueFromParameter() {
        Date value = null;
        if (!TextUtils.isEmpty(getParameter().getValue())) {
            if (!TextUtils.isEmpty(getParameter().getPattern()))
                try {
                    value = new SimpleDateFormat(getParameter().getPattern(), Locale.getDefault()).parse(getParameter().getValue());
                } catch (ParseException e) {
                    Log.e(TAG, "Не удалось распарсить значение в дату используя шаблон: " + getParameter().getPattern(), e);
                }
            if (value == null)
                try {
                    //Если шаблона для даты нету или распарсить не удалось, пробуем распарсить в стандартон Java формате.
                    value = new SimpleDateFormat(FORMAT_DATE_TIME_DEFAULT, Locale.getDefault()).parse(getParameter().getValue());
                } catch (ParseException e) {
                    Log.e(TAG, "Не удалось распарсить значение в дату без pattern'а даты", e);
                }
        }
        return value;
    }

    @Override
    public String getEditedValue() {
        if (!isValidValue())
            return null;

        return new SimpleDateFormat(
                !TextUtils.isEmpty(getParameter().getPattern()) ? getParameter().getPattern() : FORMAT_DATE_TIME_DEFAULT,
                Locale.getDefault()).format(value);

    }

    @Override
    public String getUserRepresentationValue() {
        if (value == null)
            return null;
        return new SimpleDateFormat(isContainTime ? FORMAT_DATE_TIME_FOR_USER : FORMAT_DATE_FOR_USER, Locale.getDefault()).format(value);
    }

    @Override
    public boolean isValidValue() {
        return value != null;
    }

    private void openDatePickerDialog() {
        if (!(getContext() instanceof Activity))
            return;
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.setListener(this);
        datePickerDialogFragment.setDate(value);
        datePickerDialogFragment.show(((Activity) getContext()).getFragmentManager(), null);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        selectedDateCalendar = Calendar.getInstance();
        selectedDateCalendar.set(year, monthOfYear, dayOfMonth);

        if (isContainTime) {
            openTimePickerDialog();
        } else {
            setValue(selectedDateCalendar.getTime());
            if (valueChangeListener != null)
                valueChangeListener.onChange(this);
        }
    }

    private void openTimePickerDialog() {
        if (!(getContext() instanceof Activity))
            return;
        if (timePickerDialogFragment != null)
            return;

        timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.setListener(this);
        timePickerDialogFragment.setDate(value);
        timePickerDialogFragment.show(((Activity) getContext()).getFragmentManager(), null);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timePickerDialogFragment = null;
        selectedDateCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        selectedDateCalendar.set(Calendar.MINUTE, minute);

        setValue(selectedDateCalendar.getTime());
        if (valueChangeListener != null)
            valueChangeListener.onChange(this);
    }

    private void setValue(Date newValue) {
        value = newValue;
        if (value != null) {
            SimpleDateFormat format = new SimpleDateFormat(isContainTime ? FORMAT_DATE_TIME_FOR_USER : FORMAT_DATE_FOR_USER, Locale.getDefault());
            textDate.setVisibility(View.VISIBLE);
            viewDateCap.setVisibility(View.GONE);
            textDate.setText(format.format(value));
        } else {
            viewDateCap.setVisibility(View.VISIBLE);
            textDate.setVisibility(View.GONE);
        }
    }

}
