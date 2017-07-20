package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.bpc.modulesdk.utils.Currency;

import java.util.ArrayList;
import java.util.List;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by dzmitrystrupinski on 4/21/17.
 */

public class SelectCurrencyView extends LinearLayoutCompat {
    private Spinner spinner;
    private List<Currency> currencies;

    public SelectCurrencyView(Context context) {
        super(context);
        init();
    }

    public SelectCurrencyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectCurrencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setup(Context context, List<Currency> currencies) {
        this.currencies = currencies;
        spinner.setAdapter(new CurrencyAdapter(context, currencies));
    }

    public Currency getSelectedCurrency() {
        Currency currency = null;
        if (spinner.getSelectedItem() != null) {
            currency = Currency.identify(spinner.getSelectedItem().toString());
        }
        return currency;
    }

    public void setSelectedCurrency(Currency selectedCurrency) {
        if (selectedCurrency != null) {
            int currencyIndex = -1;
            for (int index = 0; index < currencies.size(); index++) {
                Currency currency = currencies.get(index);
                if (selectedCurrency.equals(currency)) {
                    currencyIndex = index;
                    break;
                }
            }
            if (currencyIndex > -1) {
                spinner.setSelection(currencyIndex);
            }
        }
    }

    public void setEnabled(boolean enabled) {
        spinner.setEnabled(enabled);
    }

    // Private methods

    private void init() {
        inflate(getContext(), R.layout.view_select_currency, this);
        bindViews();
    }

    private void bindViews() {
        spinner = (Spinner) findViewById(R.id.currency_spinner);
    }

    private class CurrencyAdapter extends ArrayAdapter<String> {

        public CurrencyAdapter(Context context, List<Currency> items) {
            super(context, -1);

            List<String> currencies = new ArrayList<>(items.size());
            for (Currency item : items) {
                currencies.add(item.toString());
            }

            addAll(currencies);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            TextView textView = (TextView) convertView;
            if (textView == null) {
                textView = (TextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
                textView.setGravity(Gravity.RIGHT);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color_main));
            }
            textView.setText(getItem(position));

            return textView;
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            TextView textView = (TextView) convertView;
            if (textView == null) {
                textView = (TextView) LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                textView.setGravity(Gravity.RIGHT);
            }
            textView.setText(getItem(position));

            return textView;
        }

    }
}
