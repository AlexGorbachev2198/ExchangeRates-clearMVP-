package com.example.red_ragnar.testing;

import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_eur;
import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_rub;
import static ru.bpc.mobilebanksdk.R.drawable.ic_currency_usd;

/**
 * Created by Red_Ragnar on 14.07.2017.
 */

public interface IView {
    void ChangePicFromButton(String rate);

    void ChangePicToButton(String rate);

    void SetText(String data);

    String GetUsdFrom();

    String GetUsdTo();

    void SetUsdFrom(String rate);

    void SetUsdTo(String rate);
}
