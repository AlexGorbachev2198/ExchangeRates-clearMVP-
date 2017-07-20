package com.bpc.modulesdk.utils;

import com.bpc.modulesdk.rest.dto.pojo.Account;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class ResourceHelper {

    public static int mapCurrencyIconDark(String curName) {
        Currency currency = Currency.identify(curName);
        switch (currency) {
            case EUR:
                return R.drawable.ic_currency_eur;
            case RUR:
            case RUB:
                return R.drawable.ic_currency_rub;
            case USD:
                return R.drawable.ic_currency_usd;
            default:
                return R.drawable.invisible_pixel;
        }
    }

    public static int mapCurrencyIconLight(String curName) {
        Currency currency = Currency.identify(curName);
        switch (currency) {
            case EUR:
                return R.drawable.ic_currency_eur_white;
            case RUR:
            case RUB:
                return R.drawable.ic_currency_rub_white;
            case USD:
                return R.drawable.ic_currency_usd_white;
            default:
                return R.drawable.invisible_pixel;
        }
    }

    public static int mapAccountType(Account.Type type) {
        switch (type) {
            case CHECKING:
                return R.string.checking;
            case DEPOSIT:
                return R.string.deposit;
            case CREDIT:
                return R.string.credit;
            default:
                return R.string.unknown;
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

    public static int mapAccountStatus(Account.Status status) {
        switch (status) {
            case PERM_BLOCK:
                return R.string.account_status_perm_block;
            case TEMP_BLOCK:
                return R.string.account_status_temp_block;
            case VALID:
                return R.string.account_status_valid;
            default:
                return R.string.status_unknown;
        }
    }
}
