package com.bpc.modulesdk.utils;

/**
 * Created by Masloed on 25.01.2017.
 */

public class Formatter {

    public static String getCardNumber(String rowCardNumber) {
        rowCardNumber = rowCardNumber.replaceAll("\\s+", "");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rowCardNumber.length(); i++) {
            if (i % 4 == 0 && i != 0) {
                result.append(" ");
            }

            result.append(rowCardNumber.charAt(i));
        }
        return result.toString();
    }
}
