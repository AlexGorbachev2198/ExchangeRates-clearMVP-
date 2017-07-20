package com.bpc.modulesdk.utils;

/**
 * Created by Samoylov on 20.01.2017.
 */

public enum Currency {
    RUR, RUB, US, USD, EUR, UNKNOWN;

    public static Currency identify(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty())
            return Currency.UNKNOWN;
        try {
            return Currency.valueOf(currencyCode);
        } catch (IllegalArgumentException e) {
            return Currency.UNKNOWN;
        }
    }
}