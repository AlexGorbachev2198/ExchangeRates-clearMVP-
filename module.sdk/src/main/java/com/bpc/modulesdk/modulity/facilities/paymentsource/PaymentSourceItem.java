package com.bpc.modulesdk.modulity.facilities.paymentsource;

import android.content.Context;
import android.view.View;

import com.bpc.modulesdk.rest.dto.pojo.Card;
import com.bpc.modulesdk.rest.dto.pojo.PaymentSourceType;
import com.bpc.modulesdk.rest.dto.pojo.StorageEntry;

import java.io.Serializable;
import java.util.List;

/*import ru.bpc.mobilebanksdk.dto.item.StorageEntry;
import ru.bpc.mobilebanksdk.dto.item.StorageOptionsEntry;
import ru.bpc.mobilebanksdk.helpers.PaymentSourceType;*/

/**
 * Created by Samoylov on 14.12.2015.
 * <p>
 * Источник бабла для перевода. К примеру: счёт, карта.
 */
public abstract class PaymentSourceItem implements Serializable {

    public static String EXTRA_KEY = "payment_source";

    /** Id источника отправляемы на севрер */
    private String sourceID;
    /** Тип источника отправляемы на севрер */
    private PaymentSourceType sourceType;
    private String currency;
    /** Карта, для карточного счёта*/
    private PaymentSourceItem parent;

    public PaymentSourceItem(String sourceID, PaymentSourceType sourceType, String currency) {
        this(sourceID, sourceType, currency, null);
    }

    public PaymentSourceItem(String sourceID, PaymentSourceType sourceType, String currency, PaymentSourceItem parent) {
        this.sourceID = sourceID;
        this.sourceType = sourceType;
        this.currency = currency;
        this.parent = parent;
    }

    public PaymentSourceType getSourceType() {
        return sourceType;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSourceID() {
        return sourceID;
    }

    public PaymentSourceItem getParent() {
        return parent;
    }

    public abstract View getView(Context context);

    public abstract StorageEntry toStorageEntry();

    //public abstract boolean isAccountEntryAllowed(List<Account.Type> availableTypes);

    public abstract boolean isCardEntryAllowed(List<Card.Type> availableTypes);
}
