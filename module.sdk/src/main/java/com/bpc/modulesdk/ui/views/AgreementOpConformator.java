package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.entries.ConfirmRequestDescriptor;
import com.bpc.modulesdk.utils.callback.Callback;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Samoylov on 21.09.2016.
 */

public class AgreementOpConformator implements OpConfirmator {

    private Context context;
    private ConfirmRequestDescriptor descriptor;

    public AgreementOpConformator(Context context, ConfirmRequestDescriptor descriptor) {
        this.context = context;
        this.descriptor = descriptor;
    }

    @Override
    public View getView() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_op_confirmation_agrement, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(descriptor.getAgreementInfo());
        return view;
    }

    @Override
    public String getConfirmVal() {
        return "ok";
    }

    @Override
    public void setConfirmVal(String confirmVal) {
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void setActions(Callback onCompleteCallback, Callback onChangeCallback) {
        if (onChangeCallback != null)
            onChangeCallback.action();
    }

    @Override
    public int getErrorMessage() {
        return R.string.error;
    }

    @Override
    public boolean handleConfirmationError() {
        return false;
    }
}