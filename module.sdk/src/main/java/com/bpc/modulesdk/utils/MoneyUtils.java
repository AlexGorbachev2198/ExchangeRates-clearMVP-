package com.bpc.modulesdk.utils;


import android.support.annotation.NonNull;

import com.bpc.modulesdk.rest.dto.pojo.entries.MoneyEntry;

/**
 * Created by Masloed on 18.01.2017.
 */

public class MoneyUtils {
    @NonNull
    public static String moneyString(MoneyEntry money) {
        StringBuilder builder = new StringBuilder();
        if (money != null) {
            builder.append(money.getAmount())
                    .append(" ")
                    .append(money.getCurrency());
        }

        return builder.toString();
    }
}
