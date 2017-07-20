package com.bpc.modulesdk.fragment.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Siarhei Mikevich on 5/24/17.
 */

public class TimePickerDialogFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener listener;
    private Date date;

    public void setListener(TimePickerDialog.OnTimeSetListener listener){
        this.listener = listener;
    }

    public void setDate(Date date){
        this.date = date;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        if(date != null)
            calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), listener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

}
