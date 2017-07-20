package com.bpc.modulesdk.ui.views;

/**
 * Created by Smolyaninov on 24.01.2017.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.bpc.mobilebanksdk.R;


/**
 * Created by Masloed on 05.06.2015.
 */
public class ContinueButton extends LinearLayout {

    public final static int BUTTON_DISABLE = 0;
    public final static int BUTTON_ENABLE = 1;
    public final static int BUTTON_DISABLE_LOADING = 2;

    public final static int LABEL_STYLE_NORMAL = 0;
    public final static int LABEL_STYLE_SMALL = 1;

    public final static int BUTTON_STYLE_MAIN = 0;
    public final static int BUTTON_STYLE_LOGIN = 1;

    private LinearLayout button;
    private TextView textView;
    private ImageView arrow;
    private ProgressBar progressBar;

    private String label;
    private int state = BUTTON_ENABLE;
    private int labelStyle = LABEL_STYLE_NORMAL;
    private int buttonStyle = BUTTON_STYLE_MAIN;

    public ContinueButton(Context context) {
        super(context);
        initView();
    }

    public ContinueButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        initView();
    }

    public ContinueButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContinueButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
        initView();
    }

    private void initView() {
        if (isInEditMode()) {
            setBackgroundColor(Color.parseColor("#ffee55"));
            TextView textView = new TextView(getContext());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setLayoutParams(params);
            textView.setText(label != null ? label : ContinueButton.class.getSimpleName());

            float scale = getResources().getDisplayMetrics().density;
            textView.setPadding((int) (30 * scale + 0.5f),
                    (int) (10 * scale + 0.5f),
                    (int) (30 * scale + 0.5f),
                    (int) (10 * scale + 0.5f));
            addView(textView);
            return;
        }

        int layoutRes = R.layout.view_continue_button_style_main;
        if (buttonStyle == BUTTON_STYLE_LOGIN) {
            layoutRes = R.layout.view_continue_button_style_login;
            //Set background here for automatically enabling/disabling
            setBackgroundResource(R.drawable.bg_button_continue_enter);
        } else {
            //Set background here for automatically enabling/disabling
            setBackgroundResource(R.drawable.bg_button_continue_main);
        }
        LayoutInflater.from(getContext()).inflate(layoutRes, this);
        button = (LinearLayout) findViewById(R.id.custom_button_continue);
        arrow = (ImageView) findViewById(R.id.custom_continue_enable);
        progressBar = (ProgressBar) findViewById(R.id.custom_progress_bar);
        textView = (TextView) findViewById(R.id.custom_button_continue_text);
        if (labelStyle == LABEL_STYLE_SMALL)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize() * 0.7f);
        textView.setText(label);

        int state = this.state;
        this.state = -1;
        setButtonState(state);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ContinueButton,
                0, 0);
        try {
            label = a.getString(R.styleable.ContinueButton_label);
            state = a.getInteger(R.styleable.ContinueButton_state, BUTTON_ENABLE);
            labelStyle = a.getInteger(R.styleable.ContinueButton_labelStyle, LABEL_STYLE_NORMAL);
            buttonStyle = a.getInteger(R.styleable.ContinueButton_style, BUTTON_STYLE_MAIN);
        } finally {
            a.recycle();
        }
    }

    public void setLabel(String label) {
        this.label = label;
        textView.setText(label);
    }

    public void setButtonState(int state) {
        if (this.state == state)
            return;
        this.state = state;
        int progressBarVisibility;
        int arrowVisibility;
        boolean buttonEnable;
        switch (this.state) {
            case BUTTON_DISABLE:
                progressBarVisibility = View.INVISIBLE;
                arrowVisibility = View.INVISIBLE;
                buttonEnable = false;
                break;
            case BUTTON_DISABLE_LOADING:
                progressBarVisibility = View.VISIBLE;
                arrowVisibility = View.INVISIBLE;
                buttonEnable = false;
                break;
            default:
                progressBarVisibility = View.INVISIBLE;
                arrowVisibility = View.VISIBLE;
                buttonEnable = true;
                break;

        }
        this.setEnabled(buttonEnable);
        this.setClickable(buttonEnable);
        arrow.setVisibility(arrowVisibility);
        progressBar.setVisibility(progressBarVisibility);
    }

    public int getState() {
        return state;
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setText(int textResID) {
        textView.setText(textResID);
    }

    public void setButtonDisableWithDelay(int delayInMilliseconds) {
        setButtonState(BUTTON_DISABLE);
        this.postDelayed(() -> setButtonState(BUTTON_ENABLE), delayInMilliseconds);
    }

}
