package com.bpc.modulesdk.ui.widgets.currencyrateswidget.nameswidget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Set;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 03.02.2017.
 */

public class CurrencyNamesWidget extends FrameLayout {

    private CurrencyNameView name1;
    private CurrencyNameView name2;

    private ImageView swap_button;

    private Set<String> names;

    public CurrencyNamesWidget(Context context) {
        super(context);
    }

    public CurrencyNamesWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrencyNamesWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CurrencyNamesWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void init(Set<String> namesList) {
        this.names = namesList;
        LayoutInflater.from(getContext()).inflate(R.layout.widget_currency_names, this);
        name1 = (CurrencyNameView) findViewById(R.id.currency_Names);
        name2 = (CurrencyNameView) findViewById(R.id.currency_Names2);

        name1.init(name2, 0, names, 1);
        name2.init(name1, 1, names, 2);

        swap_button = (ImageView) findViewById(R.id.swap_rates_button);
        swap_button.setOnClickListener(view -> swap());
    }

    public void swap() {
        name1.swap();
    }

    public CurrencyNameView getName2() {
        return name2;
    }

    public CurrencyNameView getName1() {
        return name1;
    }
}
