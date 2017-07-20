package com.bpc.modulesdk.fragment.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;

import com.bpc.modulesdk.BaseApp;

import java.util.List;

/**
 * Created by Samoylov on 27.10.2015.
 */
public class SelectDialogFragment extends DialogFragment {

    private DialogInterface.OnClickListener listener;
    private CharSequence[] values;
    private String title;

    public void setListener(DialogInterface.OnClickListener listener) {
        this.listener = listener;
    }

    public void setValues(List<String> values) {
        this.values = values.toArray(new CharSequence[values.size()]);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(@StringRes int title) {
        this.title = BaseApp.getContext().getResources().getString(title);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setItems(values, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onClick(dialog, which);
                    }
                });
        return builder.create();
    }

}
