package com.bpc.modulesdk.ui.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;
import com.bpc.modulesdk.utils.Currency;
import com.bpc.modulesdk.utils.CurrencyHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.bpc.mobilebanksdk.R;

public class AmountCurrencyEditText extends LinearLayout {

    private final String TAG = "AmountCurrencyEditText";

    private TextInputLayout viewAmount;
    private AmountEditText editAmount;
    private Spinner spinnerCurrency;

    public AmountCurrencyEditText(Context context) {
        super(context);
        init();
    }

    public AmountCurrencyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttr(attrs);
    }

    public AmountCurrencyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttr(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AmountCurrencyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        initAttr(attrs);
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.view_amount_currency_edit_text, this);
        bindView();

        if (isInEditMode()) {
            setBackgroundColor(Color.parseColor("#ffee55"));
            editAmount.setHint("Enter amount");
            return;
        }

    }

    public boolean isValidAmount() {
        boolean one = getAmount().length() > 0;
        boolean two = getAmountFloat() >= 0.01f;
        return one && two;
    }

    public void addTextChangeListener(TextWatcher watcher) {
        editAmount.addTextChangedListener(watcher);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AmountCurrencyEditText,
                0, 0);

        try {
            editAmount.setHint(a.getString(R.styleable.AmountCurrencyEditText_hint));
            editAmount.setText(a.getString(R.styleable.AmountCurrencyEditText_text));
            editAmount.setRound(a.getBoolean(R.styleable.AmountCurrencyEditText_value_round, false));
        } finally {
            a.recycle();
        }
    }

    private void bindView() {
        viewAmount = (TextInputLayout) findViewById(R.id.amount_currency_view_amount);
        editAmount = (AmountEditText) findViewById(R.id.amount_currency_edit_amount);
        spinnerCurrency = (Spinner) findViewById(R.id.amount_currency_spinner_currency);
    }

    public void setCurrencies(List<Currency> currencies) {
        if (currencies == null)
            return;
        setCurrencyAdapter(new CurrencyAdapter(getContext(), currencies));
    }

    public void setCurrencies(String[] currencies) {
        if (currencies == null)
            return;
        setCurrencyAdapter(new CurrencyAdapter(getContext(), currencies));
    }

    private void setCurrencyAdapter(ArrayAdapter adapter) {
        spinnerCurrency.setAdapter(adapter);
        if (adapter.getCount() == 1)
            setCurrencyEditable(false);
    }

    public void setSelectedCurrency(String currStr) {
        SpinnerAdapter adapter = spinnerCurrency.getAdapter();
        if (adapter instanceof ArrayAdapter) {
            int position = ((ArrayAdapter<String>) adapter).getPosition(currStr);
            if (position >= 0) {
                spinnerCurrency.setSelection(position);
                return;
            }
        }
    }

    public void setCurrencyEditable(boolean isEditable) {
        spinnerCurrency.setClickable(isEditable);
        spinnerCurrency.setEnabled(isEditable);
    }

    public String getSelectedCurrency() {
        return (String) spinnerCurrency.getSelectedItem();
    }

    public String getAmount() {
        return editAmount.getText().toString();
    }

    public MoneyEntry getAmountEntry(Activity context) {
        MoneyEntry entry = new MoneyEntry();

        try {
            entry.setAmount(BigDecimal.valueOf(Double.parseDouble(getAmount())));
            entry.setAmount(BigDecimal.valueOf(getAmountFloat()));
        } catch (Exception e) {
            Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        entry.setCurrency(getSelectedCurrency());

        return entry;
    }

    public BigDecimal getParsedAmount() {
        return CurrencyHelper.parse(getAmount());
    }

    public float getAmountFloat() {
        try {
            return Float.parseFloat(getAmount());
        } catch (NumberFormatException e) {
            Log.e(TAG, "Cannot format value to float. Value: " + getAmount(), e);
            return 0.0f;
        }
    }

    public void setAmount(CharSequence amount) {
        editAmount.setText(amount);
    }

    public MoneyEntry getAmountEntry() {
        MoneyEntry entry = new MoneyEntry();

        try {
            entry.setAmount(BigDecimal.valueOf(Double.parseDouble(getAmount())));
            entry.setAmount(BigDecimal.valueOf(getAmountFloat()));
        } catch (Exception e) {
            //Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        entry.setCurrency(getSelectedCurrency());

        return entry;
    }

    /*public void addAmountErrorWatcher() {
        editAmount.addTextChangedListener(new AmountErrorWatcher(viewAmount));
    }

    public void addTextChangedListener(TextWatcher watcher) {
        editAmount.addTextChangedListener(watcher);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener listener) {
        editAmount.setOnEditorActionListener(listener);
    }

    public void setOnFocusChangeListener(View.OnFocusChangeListener listener) {
        editAmount.setOnFocusChangeListener(listener);
    }

    public boolean isValidValue() {
        return getSelectedCurrency() != null && CurrencyHelper.parse(getAmount()).compareTo(BigDecimal.ZERO) > 0;
    }*/

    /**
     * Add to {@code moneySourceView} {@link MoneySourceView.OnChangeListener} and take currency from it.
     *
     * @param moneySourceView view where user select source
     */
    public void setMoneySourceView(MoneySourceView moneySourceView) {
        if (moneySourceView != null)
            moneySourceView.addOnChangeListener(source -> {
                if (source != null) {
                    setSelectedCurrency(source.getCurrency());
                }
            });
    }


    public class CurrencyAdapter extends ArrayAdapter<String> {

        public CurrencyAdapter(Context context, String[] currencies) {
            super(context, -1, currencies);
        }

        public CurrencyAdapter(Context context, List<Currency> records) {
            super(context, -1);

            List<String> currencies = new ArrayList<>(records.size());
            for (Currency record : records) {
                currencies.add(record.toString());
            }

            addAll(currencies);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            TextView textView = (TextView) convertView;
            if (textView == null) {
                textView = (TextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_main));
            }
            textView.setText(CurrencyHelper.getCurrencyName(getContext(), getItem(position)));
            return textView;
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            TextView textView = (TextView) convertView;
            if (textView == null) {
                textView = (TextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                textView.setGravity(Gravity.CENTER);
            }
            textView.setText(CurrencyHelper.getCurrencyName(getContext(), getItem(position)));
            return textView;
        }

    }
}
