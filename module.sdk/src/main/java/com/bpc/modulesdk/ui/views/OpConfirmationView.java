package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.ModulePropertiesProvider;
import com.bpc.modulesdk.rest.dto.pojo.entries.ConfirmRequestDescriptor;
import com.bpc.modulesdk.settings.ModulePropertiesKey;
import com.bpc.modulesdk.ui.watchers.AfterTextChangedTextWatcher;
import com.bpc.modulesdk.utils.callback.Callback;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Masloed on 27.11.2015.
 */
//TODO Необходимо разделить на три разных OpConfirmator'а
public class OpConfirmationView extends LinearLayout implements OpConfirmator {

    public final static int TYPE_PASSWORD = 0;
    public final static int TYPE_OTP = 1;
    public final static int TYPE_SCRATCH = 2;

    private int confirmType;
    private View passView;
    private View otpView;
    private View scratchView;

    private EditText passEditText;
    private EditText otpEditText;
    private EditText scratchEditText;

    private TextInputLayout passWrapper;
    private TextInputLayout otpWrapper;
    private TextInputLayout scratchWrapper;

    private TextView textGetCodeAgain;
    private TextView otpTitle;
    private TextView scratchTitle;

    private View viewConformationCode;
    private TextView textGetCode;
    private ProgressBar progressBar;

    public OpConfirmationView(Context context) {
        super(context);
        initView();
    }

    private void setType(int type) {
        if (type != TYPE_PASSWORD && type != TYPE_OTP && type != TYPE_SCRATCH)
            type = TYPE_PASSWORD;
        this.confirmType = type;
        passView.setVisibility(type == TYPE_PASSWORD ? VISIBLE : GONE);
        otpView.setVisibility(type == TYPE_OTP ? VISIBLE : GONE);
        scratchView.setVisibility(type == TYPE_SCRATCH ? VISIBLE : GONE);
    }

    public void setType(ConfirmRequestDescriptor confDescriptor) {
        switch (confDescriptor.getType()) {
            case OTP:
                setType(OpConfirmationView.TYPE_OTP);
                break;
            case PASSWORD:
                setType(OpConfirmationView.TYPE_PASSWORD);
                break;
            case SCRATCHCODE:
                setType(OpConfirmationView.TYPE_SCRATCH);
                break;
            default:
                setType(OpConfirmationView.TYPE_PASSWORD);
                break;
        }
    }

    private void addTextChangedListener(TextWatcher watcher) {
        EditText ed = getEditText();
        if (ed != null) ed.addTextChangedListener(watcher);
    }

    private TextInputLayout getTextInputLayout() {
        switch (confirmType) {
            case TYPE_OTP:
                return otpWrapper;
            case TYPE_PASSWORD:
                return passWrapper;
            case TYPE_SCRATCH:
                return scratchWrapper;
            default:
                return passWrapper;
        }
    }

    public void setTransRef(final String transRef) {
        if (confirmType != TYPE_OTP)
            return;
        textGetCodeAgain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                requestConfirmationCode(viewConformationCode, transRef);
            }
        });
        textGetCode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                requestConfirmationCode(textGetCode, transRef);
            }
        });
    }

    public void setScratchCard(String card, String id) {
        scratchTitle.setText(String.format(getContext().getString(R.string.op_confirm_scratch_title), id, card));
    }

    private void setOTPOnlyDigits() {
        if (otpEditText != null) {
            otpEditText.setInputType(InputType.TYPE_CLASS_PHONE);
            scratchEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        }
    }

    private void initView() {
        if (isInEditMode()) {
            setBackgroundColor(Color.parseColor("#ffee55"));
            TextView textView = new TextView(getContext());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setLayoutParams(params);
            textView.setText(OpConfirmationView.class.getSimpleName());
            addView(textView);
            return;
        }

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_op_confirmation, this);
        passView = findViewById(R.id.confirmation_group_pass);
        otpView = findViewById(R.id.confirmation_group_otp);
        scratchView = findViewById(R.id.confirmation_group_scratch);

        passEditText = (EditText) findViewById(R.id.confirmation_code_pass);
        otpEditText = (EditText) findViewById(R.id.confirmation_code_otp);
        scratchEditText = (EditText) findViewById(R.id.confirmation_code_scratch);

        passWrapper = (TextInputLayout) findViewById(R.id.confirmation_code_pass_wrap);
        otpWrapper = (TextInputLayout) findViewById(R.id.confirmation_code_otp_wrap);
        scratchWrapper = (TextInputLayout) findViewById(R.id.confirmation_code_scratch_wrap);

        //TODO
        //addTextChangedListener(new CharacterCountErrorWatcher(getTextInputLayout(), 0, 0));
        setType(confirmType);

        textGetCodeAgain = (TextView) findViewById(R.id.text_get_code_again);
        otpTitle = (TextView) findViewById(R.id.confirmation_code_otp_title);
        scratchTitle = (TextView) findViewById(R.id.confirmation_code_scratch_title);

        viewConformationCode = findViewById(R.id.view_conformation_code);
        textGetCode = (TextView) findViewById(R.id.text_get_code);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //ModulePropertiesProvider propertiesProvider = new ModulePropertiesProvider(BaseBankApplication.getApp());
        ModulePropertiesProvider propertiesProvider = new ModulePropertiesProvider(BaseApp.getApp());
        if (propertiesProvider.getBoolean(ModulePropertiesKey.OTP_DIGITS_REQUIRED, false))
            setOTPOnlyDigits();
    }

    private EditText getEditText() {
        switch (confirmType) {
            case TYPE_OTP:
                return otpEditText;
            case TYPE_PASSWORD:
                return passEditText;
            case TYPE_SCRATCH:
                return scratchEditText;
            default:
                return passEditText;
        }
    }

    /**
     * Выполняет запрос кода подтверждения
     *
     * @param requestView - view, которая была видна перед выполнением запроса. При не успешном выполнении запроса отобразит эту view.
     */
    private void requestConfirmationCode(final View requestView, String transRef) {

        /*progressBar.setVisibility(View.VISIBLE);
        requestView.setVisibility(View.INVISIBLE);
        otpEditText.setText("");

        final ErrorHandler errorHandler = new ErrorHandler(getContext());
        WebService.getGetOTPbySMSRequest(transRef)
                .subscribe(new Action1<MainResponse>() {
                    @Override
                    public void call(MainResponse response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        textGetCode.setVisibility(View.INVISIBLE);
                        viewConformationCode.setVisibility(View.VISIBLE);
                        otpTitle.setText(R.string.op_confirm_otp_sended);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        errorHandler.handle(throwable);
                        progressBar.setVisibility(View.INVISIBLE);
                        requestView.setVisibility(View.VISIBLE);
                    }
                });*/
    }


    @Override
    public View getView() {
        return this;
    }

    @Override
    public String getConfirmVal() {
        EditText ed = getEditText();
        if (ed != null) return ed.getText().toString();
        return null;
    }

    @Override
    public void setConfirmVal(String confirmVal) {
        EditText ed = getEditText();
        if (ed != null) ed.setText(confirmVal);
    }

    @Override
    public boolean isValid() {
        String confirmVal = getConfirmVal();
        return confirmVal != null && !confirmVal.isEmpty();
    }

    @Override
    public void setActions(final Callback onCompleteCallback, final Callback onChangeCallback) {
        EditText editText = getEditText();
        if (editText == null)
            return;

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (onCompleteCallback != null) onCompleteCallback.action();
                }
                return false;
            }
        });

        editText.addTextChangedListener(new AfterTextChangedTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (onChangeCallback != null) onChangeCallback.action();
            }
        });
        //TODO editText.addTextChangedListener(new CharacterCountErrorWatcher(getTextInputLayout(), 0, 0));
    }

    @Override
    public int getErrorMessage() {
        //TODO
        /*
        if (confirmType == TYPE_OTP)
            return R.string.error_incorrect_otp;
        else if (confirmType == TYPE_SCRATCH)
            return R.string.error_incorrect_scratch;

        return R.string.error_incorrect_pass;*/
        return 0;
    }

    @Override
    public boolean handleConfirmationError() {
        return false;
    }
}
