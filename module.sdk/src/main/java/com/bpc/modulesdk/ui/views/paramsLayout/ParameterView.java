package com.bpc.modulesdk.ui.views.paramsLayout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bpc.modulesdk.ui.interfaces.ActivityResultRequester;
import com.bpc.modulesdk.ui.interfaces.RequestPermissionRequester;
import com.bpc.modulesdk.utils.DialogHelper;

import ru.bpc.mobilebanksdk.R;


/**
 * Created by Samoylov on 19.10.2015.
 */
public abstract class ParameterView extends LinearLayout {

    private String titleInfoTitle = null;
    private String messageInfoTitle = null;
    private ParameterRecord parameter;
    protected OnValueChangeListener valueChangeListener;
    protected RequestPermissionRequester permissionReceiver;
    protected ActivityResultRequester resultRequester;

    public ParameterView(Context context, ParameterRecord parameter, RequestPermissionRequester permissionReceiver,
                         ActivityResultRequester resultRequester, boolean isEditable) {
        super(context);
        this.parameter = parameter;
        this.permissionReceiver = permissionReceiver;
        this.resultRequester = resultRequester;
        initView(isEditable);
        if(isEditable)
            initEditMode();
        else
            initNotEditMode();
    }

    private void initView(boolean isEditable) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_parameter_title, this);
        TextView textTitle = (TextView) findViewById(R.id.text_title);
        ImageView imageInfoTitle = (ImageView) findViewById(R.id.image_info_title);
        ImageView imageMandatory = (ImageView) findViewById(R.id.image_mandatory);

        setOrientation(VERTICAL);

        setInfoTitleDialog(parameter.getName(), parameter.getDescription());
        textTitle.setText(parameter.getName());
        if (isEditable && messageInfoTitle != null && !messageInfoTitle.isEmpty())
            imageInfoTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfoTitleDialog();
                }
            });
        else
            imageInfoTitle.setVisibility(View.GONE);
        imageMandatory.setVisibility(parameter.isMandatory() ? View.VISIBLE : View.GONE);
    }

    protected void initNotEditMode(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_parameter_not_edit_default, this);
        TextView textParameterValue = (TextView) findViewById(R.id.text_parameter_value);
        textParameterValue.setText(getUserRepresentationValue());
    }

    private void showInfoTitleDialog() {
        if (messageInfoTitle == null || messageInfoTitle.isEmpty())
            return;
        DialogHelper.showInfoDialog(getContext(), titleInfoTitle, messageInfoTitle);
    }

    public void setInfoTitleDialog(String title, String message) {
        this.titleInfoTitle = title;
        this.messageInfoTitle = message;
    }

    public ParameterRecord getParameter() {
        return parameter;
    }

    public void setOnValueChangeListener(OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }

    protected abstract void initEditMode();

    public abstract String getEditedValue();

    public abstract String getUserRepresentationValue();

    public abstract boolean isValidValue();

    /**
     * Need to override if you want to receive Intent after  ActivityResultRequester#startActivityForResult(ActivityResultReceiver, Intent)}
     *
     * @param data - data that received from result
     */
    public void receive(Intent data) {
    }

    /**
     * Need to override if you want to receive action after {@link ParameterView.Receiver#needRequestPermissions(ParameterView parameterView, String[] permissions, int permissionRationaleTitle, int permissionRationaleMessage)}
     */
    public void permissionsAllowed() {
    }

    public interface OnValueChangeListener {
        void onChange(ParameterView parameterView);
    }

    public interface Receiver extends ActivityResultRequester {

        /**
         * @param parameterView
         * @param permissions
         * @param permissionRationaleTitle
         * @param permissionRationaleMessage
         * @see ParameterView#permissionsAllowed()
         */
        void needRequestPermissions(ParameterView parameterView, String[] permissions, int permissionRationaleTitle, int permissionRationaleMessage);
    }
}
