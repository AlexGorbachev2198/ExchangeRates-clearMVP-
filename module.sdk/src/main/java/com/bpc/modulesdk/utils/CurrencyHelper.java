package com.bpc.modulesdk.utils;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import ru.bpc.mobilebanksdk.R;
/**
 * Created by Samoylov on 20.01.2017.
 */
public class CurrencyHelper {
    public static String format(float f) {
        return format(new BigDecimal(f), false, true);
    }
    public static String format(BigDecimal bd) {
        return format(bd, false, true);
    }
    public static String format(BigDecimal bd, boolean sign, boolean grouping) {
        if (bd == null) {
            bd = new BigDecimal(0);
        }
        DecimalFormat df = buildDecimalFormat();
        if (grouping) {
            df.setGroupingSize(3);
        } else {
            df.setGroupingUsed(false);
        }
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        if (sign) {
            df.setPositivePrefix("+");
        }
        return df.format(bd);
    }

    public static String format(BigDecimal bd, boolean sign, boolean grouping, int fractionDigits) {
        if (bd == null) {
            bd = new BigDecimal(0);
        }
        DecimalFormat df = buildDecimalFormat();
        if (grouping) {
            df.setGroupingSize(3);
        } else {
            df.setGroupingUsed(false);
        }
        df.setMaximumFractionDigits(fractionDigits);
        df.setMinimumFractionDigits(fractionDigits);
        if (sign) {
            df.setPositivePrefix("+");
        }
        return df.format(bd);
    }

    public static BigDecimal parse(String value) {
        if (value == null || value.isEmpty())
            return new BigDecimal(0);
        try {
            BigDecimal bd = (BigDecimal) buildDecimalFormat().parse(value.replace(",", "."));
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            return bd;
        } catch (ParseException e) {
            //throw new RuntimeException(e);
            Log.e("Currency Helper", "Cannot parse String to BigDecimal", e);
            return new BigDecimal(0);
        }
    }

    public static CharSequence formatAmount(
            Context ctx,
            BigDecimal amount,
            String currencyCode,
            int fractFormat,
            int currencyFormat,
            boolean sign) {
        String currency = getCurrencyName(ctx, currencyCode != null ? currencyCode : "");
        Spannable formatted = new SpannableString(format(amount, sign, true) + ' ' + currency);

        /*
        int currLength = currency.length();
        if (currencyFormat >= 0)
            formatted.setSpan(
                    new TextAppearanceSpan(ctx, currencyFormat),
                    formatted.length() - currLength,
                    formatted.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (fractFormat >= 0)
            formatted.setSpan(
                    new TextAppearanceSpan(ctx, fractFormat),
                    formatted.length() - (currLength + 4),
                    formatted.length() - currLength,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    */
        return formatted;
    }

    public static CharSequence formatAmount(Context context, BigDecimal amount, String currencyCode, boolean sign) {
        return formatAmount(context, amount, currencyCode, -1, -1, sign);
    }

    public static CharSequence formatAmount(
            Context ctx,
            BigDecimal amount,
            String currencyCode,
            int fractFormat,
            int currencyFormat) {

        return formatAmount(ctx, amount, currencyCode, fractFormat, currencyFormat, false);
    }

    public static CharSequence formatLimitAmount(
            Context ctx,
            BigDecimal amount,
            String currencyCode,
            int fractFormat,
            int currencyFormat) {

        return formatLimitAmount(ctx, amount, currencyCode, fractFormat, currencyFormat, false);
    }

    public static CharSequence formatLimitAmount(
            Context ctx,
            BigDecimal amount,
            String currencyCode,
            int fractFormat,
            int currencyFormat,
            boolean sign) {
        String currency = getCurrencyName(ctx, currencyCode);
        int currLength = currency.length();
        Spannable formatted = new SpannableString(format(amount, sign, true, 0) + ' ' + currency);
        /*formatted.setSpan(
				new TextAppearanceSpan(ctx, currencyFormat),
				formatted.length() - currLength,
				formatted.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		formatted.setSpan(
				new TextAppearanceSpan(ctx, fractFormat),
				formatted.length() - (currLength + 4),
				formatted.length() - currLength,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        return formatted;
    }

    private static DecimalFormat buildDecimalFormat() {
        DecimalFormat df = new DecimalFormat();
        df.setParseBigDecimal(true);
        DecimalFormatSymbols formatSymbols = df.getDecimalFormatSymbols();
        formatSymbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(formatSymbols);
        return df;
    }

    public static String getCurrencyName(Context ctx, String currencyCode) {
        int currNameId = mapCurrencyName(currencyCode);
        if (currNameId == -1) {
            return currencyCode;
        } else {
            return ctx.getString(currNameId);
        }
    }

    public static int mapCurrencyName(String curName) {
        Currency currency = Currency.identify(curName);
        switch (currency) {
            case EUR:
                return R.string.currency_eur;
            case RUR:
            case RUB:
                return R.string.currency_rub;
            case USD:
                return R.string.currency_usd;
            default:
                return -1;
        }
    }

    public static int getCurrencyImageRes(String curName) {
        int res;
        switch (curName) {
            case "EUR":
                res = R.drawable.ic_currency_eur;
                break;
            case "RUB":
                res = R.drawable.ic_currency_rub;
                break;
            case "USD":
                res = R.drawable.ic_currency_usd;
                break;
            default:
                res = R.drawable.ic_arrow_drop_down_black_24dp;
                break; //TODO change icon
            //TODO to enum
        }
        return res;
    }

}