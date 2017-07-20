package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 31.03.2017.
 */

@Deprecated
public class PaymentSourceItem implements CustomerListItem {

    String title;

    public PaymentSourceItem(String title) {
        this.title = title;
    }

    @Override
    public String getSectionTitle() {
        return title;
    }


    @Override
    public View getItemContent(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_payment_soruce_card, null);
        return v;
    }
}
