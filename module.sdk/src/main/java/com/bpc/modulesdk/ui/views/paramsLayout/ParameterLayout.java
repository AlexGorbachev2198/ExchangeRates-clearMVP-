package com.bpc.modulesdk.ui.views.paramsLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bpc.modulesdk.ui.interfaces.ActivityResultRequester;
import com.bpc.modulesdk.ui.interfaces.RequestPermissionRequester;

import java.util.LinkedList;
import java.util.List;

import ru.bpc.mobilebanksdk.R;



/**
 * Created by Samoylov on 11.03.2016.
 * <p/>
 * Container for ParameterViews
 */
public class ParameterLayout extends LinearLayout {

    private List<ParameterView> parametersViews = new LinkedList<>();
    private ParameterView.OnValueChangeListener listener;
    private boolean isEditable = true;

    public ParameterLayout(Context context) {
        super(context);
        init();
    }

    public ParameterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
        init();
    }

    public ParameterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParameterLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(attrs);
        init();
    }

    public void  reset(){
        removeAllViewsInLayout();
        init();
    }

    private void init() {
        setOrientation(VERTICAL);

        if (isInEditMode()) {
            setBackgroundColor(Color.parseColor("#ffee55"));
            TextView textView = new TextView(getContext());
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setLayoutParams(params);
            textView.setText(ParameterLayout.class.getSimpleName());
            addView(textView);
        }
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ParameterLayout,
                0, 0);

        try {
            isEditable = a.getBoolean(R.styleable.ParameterLayout_is_editable, isEditable);
        } finally {
            a.recycle();
        }
    }

    public void addParameters(List<ParameterRecord> parameters, RequestPermissionRequester permissionRequester,
                              ActivityResultRequester resultRequester) {
        ParameterView parameterView;
        if (parameters != null)
            for (final ParameterRecord param : parameters) {
                parameterView = create(param, permissionRequester, resultRequester, isEditable);
                if (parameterView == null)
                    continue;
                parameterView.setOnValueChangeListener(listener);
                parametersViews.add(parameterView);
                addView(decorate(parameterView));
            }
    }

    private ParameterView create(ParameterRecord parameterRecord, RequestPermissionRequester permissionRequester,
                                 ActivityResultRequester resultRequester, boolean isEditable) {
        if(parameterRecord == null)
            return null;
        switch (parameterRecord.getType()) {
            case STRING:
                return new EditTextParameterView(getContext(), parameterRecord, isEditable);
            case SELECT:
                return new SelectParameterView(getContext(), parameterRecord, isEditable);
            case PHONE:
                return new PhoneParameterView(getContext(), parameterRecord, permissionRequester, resultRequester, isEditable);
            case DATE:
                return new DateParameterView(getContext(), parameterRecord, isEditable);
           /* case STORAGE:
                return new StorageParameterView(getContext(), parameterRecord, receiver, isEditable);
            case MONEY:
                return new MoneyParameterView(getContext(), parameterRecord, isEditable);
            case STRING:
            case DIGITS:
            case EMAIL:
                return new EditTextParameterView(getContext(), parameterRecord, isEditable);*/
            default:
                return new EditTextParameterView(getContext(), parameterRecord, isEditable);
        }
    }

    private View decorate(ParameterView parameterView) {
        View result = LayoutInflater.from(getContext()).inflate(R.layout.view_parameter_layout_decor, this, false);
        ViewGroup container = (ViewGroup) result.findViewById(R.id.view_container);
        container.addView(parameterView);
        return result;
    }

    public void removeAll() {
        removeAllViews();
        parametersViews = new LinkedList<>();
    }

    public List<ParameterView> getParametersViews() {
        return parametersViews;
    }

    public void setOnValueChangeListener(ParameterView.OnValueChangeListener listener) {
        this.listener = listener;
        for (ParameterView parameterView : parametersViews)
            parameterView.setOnValueChangeListener(listener);
    }
}
