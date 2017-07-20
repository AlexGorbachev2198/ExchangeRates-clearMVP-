package com.bpc.modulesdk.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.StorageOptionsEntry;
import com.bpc.modulesdk.rest.dto.pojo.entries.CustomerAccountEntry;
import com.bpc.modulesdk.ui.interfaces.ActivityResultRequester;
import com.bpc.modulesdk.utils.CurrencyHelper;
import com.bpc.modulesdk.utils.MoneyUtils;

import java.util.LinkedList;
import java.util.List;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 31.03.2017.
 */

public class MoneySourceView extends FrameLayout /*implements ActivityResultReceiver*/ {

    private TextView textTitle;
    private ViewGroup viewContainer;
    private TextView textDescription;
    private ImageView imageArrow;

    private String title;
    private String description;
    private boolean isSelectable = true;
    private StorageOptionsEntry availableStorage;
    private boolean isNotActiveAllowed;
    //private PaymentSourceItem source;
    private CustomerAccountEntry source;

    //private SourceSelector sourceSelector = SourceSelectorFactory.getSourceSelector();
    private ActivityResultRequester activityResultRequester;
    private List<OnChangeListener> onChangeListeners = new LinkedList<>();

    public MoneySourceView(Context context) {
        super(context);
        init();
    }

    public MoneySourceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public MoneySourceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MoneySourceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MoneySourceView,
                0, 0);

        try {
            //String titleStyleable = a.getString(R.styleable.MoneySourceView_title);
            //if (titleStyleable == null || titleStyleable.isEmpty())
            title = a.getString(R.styleable.MoneySourceView_title);
            description = a.getString(R.styleable.MoneySourceView_description);
            isSelectable = a.getBoolean(R.styleable.MoneySourceView_selectable, isSelectable);
        } finally {
            a.recycle();
        }
    }

    private void init() {
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

        LayoutInflater.from(getContext()).inflate(R.layout.view_money_source, this, true);
        bindView();

        if (title == null || title.isEmpty())  textTitle.setVisibility(GONE);
        else
        textTitle.setText(title);
        textDescription.setText(description);
        setIsSelectable(isSelectable);
    }

    private void bindView() {
        textTitle = (TextView) findViewById(R.id.text_title);
        viewContainer = (ViewGroup) findViewById(R.id.view_container);
        textDescription = (TextView) findViewById(R.id.text_description);
        imageArrow = (ImageView) findViewById(R.id.image_arrow);
    }

    public void init(ActivityResultRequester activityResultRequester, StorageOptionsEntry availableStorage, boolean isNotActiveAllowed) {
        this.activityResultRequester = activityResultRequester;
        this.availableStorage = availableStorage;
        this.isNotActiveAllowed = isNotActiveAllowed;
    }

    public void init(ActivityResultRequester activityResultRequester, StorageOptionsEntry availableStorage) {
        init(activityResultRequester, availableStorage, false);
    }

    /*private void select() {
        Intent intent = sourceSelector.createIntentForSelect(getContext(), availableStorage, title, isNotActiveAllowed);
        activityResultRequester.startActivityForResult(this, intent);
    }*/

    /*@Override
    public void receive(Intent data) {
        /*PaymentSourceItem paymentSource = (PaymentSourceItem) data.getSerializableExtra(PaymentSourceItem.EXTRA_KEY);
        setSource(paymentSource);
    }*/







    public void setSource(CustomerAccountEntry source) {
        this.source = source;
        viewContainer.removeAllViews();
        if (source == null) {
            textDescription.setVisibility(VISIBLE);
            notifyOnChangeListeners();
        } else {
            textDescription.setVisibility(GONE);
            viewContainer.addView(createSourceView(source));
            notifyOnChangeListeners();
        }
    }

    private View createSourceView(CustomerAccountEntry source) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.item_payment_source, null);
        ImageView image = (ImageView) view.findViewById(R.id.item_icon);

        TextView itemTitleView = (TextView) view.findViewById(R.id.item_title);
        TextView itemTextView = (TextView) view.findViewById(R.id.item_text);
        TextView balanceTextView = (TextView) view.findViewById(R.id.item_balance);

        image.setImageResource(CurrencyHelper.getCurrencyImageRes(source.getCurrency()));

        String itemTitle = source.getName();
        if (itemTitle == null || itemTitle.isEmpty()) {
            itemTitleView.setVisibility(View.GONE);
        } else itemTitleView.setText(itemTitle);

        String itemText = source.getNumber();
        if (itemText == null || itemText.isEmpty()) {
            itemTextView.setVisibility(View.GONE);
        } else itemTextView.setText(itemText);

        String balanceText = MoneyUtils.moneyString(source.getAvailableBalance());
        if (balanceText.isEmpty()) {
            balanceTextView.setVisibility(View.GONE);
        } else balanceTextView.setText(balanceText);

        return view;
    }

    /*public void setSource(PaymentSourceItem source) {
        /*this.source = source;
        viewContainer.removeAllViews();
        if (source == null)
            textDescription.setVisibility(View.VISIBLE);
        else if (availableStorage == null || source.isAllowed(availableStorage)) {
            textDescription.setVisibility(View.GONE);
            viewContainer.addView(source.getView(getContext()));
        } else {
            this.source = null;
            return;
        }
        notifyOnChangeListeners(source);
    }*/

    public CustomerAccountEntry getSource() {
        return source;
    }

    public void setIsSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
        if (isSelectable) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //select();
                }
            });
            imageArrow.setVisibility(View.VISIBLE);
        } else {
            setOnClickListener(null);
            imageArrow.setVisibility(View.GONE);
            setClickable(false);
            setBackground(null);
        }
    }

    public void addOnChangeListener(OnChangeListener onChangeListener) {
        if (onChangeListener != null)
            onChangeListeners.add(onChangeListener);
    }

    public void removeOnChangeListener(OnChangeListener onChangeListener) {
        if (onChangeListener == null)
            return;
        int pos = onChangeListeners.indexOf(onChangeListener);
        if (pos >= 0)
            onChangeListeners.remove(pos);
    }

    public void notifyOnChangeListeners() {
        for (OnChangeListener listner : onChangeListeners)
            listner.onChange(source);
    }

    public void setDescription(@StringRes int description) {
        textDescription.setText(description);
    }

    public void setTitle(@StringRes int title) {
        textTitle.setText(title);
    }

    public interface OnChangeListener {
        void onChange(CustomerAccountEntry entry);
    }
}
