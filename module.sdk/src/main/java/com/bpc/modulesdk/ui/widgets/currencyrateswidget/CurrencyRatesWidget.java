package com.bpc.modulesdk.ui.widgets.currencyrateswidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.RateInformation;
import com.bpc.modulesdk.ui.widgets.currencyrateswidget.nameswidget.CurrencyNamesWidget;
import com.bpc.modulesdk.ui.widgets.currencyrateswidget.observerinterfaces.Observer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 17.01.2017.
 */

public class CurrencyRatesWidget extends LinearLayout implements Observer {

    private View infoGroup;

    private List<RateInformation> rates;

    private ProgressBar updateRatesProgress;
    private TextView lastUpdTextView;
    private TextView emptyTextView;

    private TextView baseValue;
    private TextView buyValue;
    private TextView sellValue;

    public CurrencyRatesWidget(Context context) {
        super(context);
    }

    public CurrencyRatesWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrencyRatesWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CurrencyRatesWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void update(String ratesCouple) {

        final Animation animationAppear = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        final Animation animationDisappear = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        animationAppear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setTextToRateValues(ratesCouple);
                rateValuesGroup.startAnimation(animationDisappear);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rateValuesGroup.startAnimation(animationAppear);
    }

    private void setTextToRateValues(String ratesCouple) {
        RateResult result = getCorrectItem(ratesCouple);
        if (result == null) {
            baseValue.setText(R.string.error_no_data);
            buyValue.setText(R.string.error_no_data);
            sellValue.setText(R.string.error_no_data);
        } else {
            RateInformation ri = result.getRi();
            boolean swapped = result.isSwapped();
            if (ri != null) {
                if (!swapped) {
                    baseValue.setText(ri.getBase().setScale(2, RoundingMode.HALF_UP).toString());
                    buyValue.setText(ri.getBuy().setScale(2, RoundingMode.HALF_UP).toString());
                    sellValue.setText(ri.getSell().setScale(2, RoundingMode.HALF_UP).toString());
                } else {
                    BigDecimal one = BigDecimal.valueOf(1);

                    baseValue.setText(one.divide(ri.getBase(), 2, RoundingMode.HALF_UP).toString());
                    buyValue.setText(one.divide(ri.getBuy(), 2, RoundingMode.HALF_UP).toString());
                    sellValue.setText(one.divide(ri.getSell(), 2, RoundingMode.HALF_UP).toString());
                }
            }
        }
    }

    public void setRates(List<RateInformation> rates) {
        this.rates = rates;
        init();
        showProgress(false);
        setUpdateDate();
    }

    public void setEmptyTextView(int textId) {
        updateRatesProgress.setVisibility(GONE);
        if (emptyTextView != null) {
            emptyTextView.setVisibility(VISIBLE);
            String text = getResources().getString(textId);
            emptyTextView.setText(text);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
        showProgress(true);
    }

    private void setUpdateDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US);
        String dateText = sdf.format(date);
        lastUpdTextView.setVisibility(VISIBLE);
        lastUpdTextView.setText(String.format(lastUpdTextView.getText().toString(), dateText));
    }

    private void showProgress(boolean isInProgress) {
        updateRatesProgress.setVisibility(isInProgress ? VISIBLE : GONE);
        infoGroup.setVisibility(isInProgress ? GONE : VISIBLE);
    }

    private Set<String> extractCurrencies() {
        Set<String> names = new HashSet<>();
        for (RateInformation info : rates) {
            String arr[] = info.getName().split("/");
            Collections.addAll(names, arr);
        }

        return names;
    }

    View rateValuesGroup;

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_currencies, this);

        updateRatesProgress = (ProgressBar) findViewById(R.id.rates_progress);

        infoGroup = findViewById(R.id.info_group);
        CurrencyNamesWidget v = (CurrencyNamesWidget) infoGroup.findViewById(R.id.currency_names_element);


        ImageView updateRatesButton = (ImageView) findViewById(R.id.update_rates);
        updateRatesButton.setVisibility(GONE);
        lastUpdTextView = (TextView) findViewById(R.id.last_update);
        lastUpdTextView.setVisibility(GONE);
        emptyTextView = (TextView) findViewById(R.id.empty_view);

        baseValue = (TextView) findViewById(R.id.base_value);
        buyValue = (TextView) findViewById(R.id.buy_value);
        sellValue = (TextView) findViewById(R.id.sell_value);

        rateValuesGroup = findViewById(R.id.rate_values_group);

        CurrencyNamesWidget currencyNamesElement = (CurrencyNamesWidget) findViewById(R.id.currency_names_element);
        if (rates != null) {
            currencyNamesElement.init(extractCurrencies());
            v.getName1().registerObserver(this);
            v.getName2().registerObserver(this);
            setTextToRateValues(v.getName1().getRatesCouple());
        }



    }

    private RateResult getCorrectItem(String ratesCouple) {
        RateResult result = null;
        for (RateInformation ri : rates) {
            String name = ri.getName();
            if (name.equals(ratesCouple)) {
                result = new RateResult(false, ri);
                break;
            } else if (name.equals(swapRatesCouple(ratesCouple))) {
                result = new RateResult(true, ri);
            }
        }
        return result;
    }

    private String swapRatesCouple(String ratesCouple) {
        String arr[] = ratesCouple.split("/");

        for (int i = 0; i < arr.length / 2; i++) {
            String temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) sb.append("/");
        }

        return sb.toString();
    }

    private class RateResult {
        private boolean swapped;
        private RateInformation ri;

        RateResult(boolean swapped, RateInformation ri) {
            this.swapped = swapped;
            this.ri = ri;
        }

        boolean isSwapped() {
            return swapped;
        }

        RateInformation getRi() {
            return ri;
        }
    }
}
