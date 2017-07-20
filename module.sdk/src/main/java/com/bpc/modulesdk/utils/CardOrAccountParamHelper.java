package com.bpc.modulesdk.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.BooleanParameter;
import com.bpc.modulesdk.rest.dto.pojo.CardOrAccountParameter;
import com.bpc.modulesdk.rest.dto.pojo.DatetimeParameter;
import com.bpc.modulesdk.rest.dto.pojo.MoneyParameter;
import com.bpc.modulesdk.rest.dto.pojo.StringParameter;
import com.bpc.modulesdk.rest.dto.pojo.StructureParameter;
import com.bpc.modulesdk.utils.CurrencyHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Masloed on 11.03.2016.
 */
public class CardOrAccountParamHelper {

    public static void fillParams(@NonNull Context ctx, @NonNull List<CardOrAccountParameter> list, @NonNull ViewGroup parametersLayout) {
        if (list == null || list.isEmpty()) {
            parametersLayout.setVisibility(View.GONE);
            return;
        }
        for (CardOrAccountParameter param : list) {
            addParam(ctx, param, parametersLayout);
        }
    }

    public static StructureParameter findStructureByPath(List<CardOrAccountParameter> list, String path) {
        CardOrAccountParameter param = findParamByPath(list, path);

        return ((param != null && CardOrAccountParameter.STRUCTURE.equals(param.getType()))
                ? (StructureParameter) param
                : null);
    }

    public static CardOrAccountParameter findParamByPath(List<CardOrAccountParameter> list, String path) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Map<String, CardOrAccountParameter> map = new HashMap<>();
        fillParamMap(map, list, null);

        return map.get(path);
    }

    public static boolean replaceSpecificParams(@NonNull Context ctx,
                                                List<CardOrAccountParameter> list,
                                                @NonNull ViewGroup parametersLayout,
                                                int paramLayoutResId,
                                                @NonNull String[] paramIdList) {

        parametersLayout.removeAllViews();
        return appendSpecificParams(ctx, list, parametersLayout, paramLayoutResId, paramIdList);
    }

    public static boolean appendSpecificParams(@NonNull Context ctx,
                                               List<CardOrAccountParameter> list,
                                               @NonNull ViewGroup parametersLayout,
                                               int paramLayoutResId,
                                               @NonNull String[] paramIdList) {

        if (list == null || list.isEmpty() || paramIdList == null || paramIdList.length == 0) {
            return false;
        }

        boolean foundAtLeastOne = false;
        Map<String, CardOrAccountParameter> map = new HashMap<>();

        fillParamMap(map, list, null);

        for (String id : paramIdList) {
            CardOrAccountParameter param = map.get(id);

            if (param != null) {
                foundAtLeastOne = true;
                addParam(ctx, param, parametersLayout, paramLayoutResId);
            }
        }

        return foundAtLeastOne;
    }

    public static void addParam(@NonNull Context ctx, @NonNull CardOrAccountParameter param, @NonNull ViewGroup parametersLayout) {
        addParam(ctx, param, parametersLayout, R.layout.card_account_parameter_item);
    }

    public static void addParam(
            @NonNull Context ctx,
            @NonNull CardOrAccountParameter param,
            @NonNull ViewGroup parametersLayout,
            int paramLayoutResId) {

        View v = LayoutInflater.from(ctx).inflate(paramLayoutResId, parametersLayout, false);
        ((TextView) v.findViewById(R.id.item_title)).setText(String.format("%s:", param.getLabel()));

        CharSequence textValue = "";
        switch (param.getType()) {
            case CardOrAccountParameter.BOOLEAN:
                textValue = ((BooleanParameter) param).isValue() ? ctx.getString(R.string.bool_true) : ctx.getString(R.string.bool_false);
                break;
            case CardOrAccountParameter.DATETIME:
                textValue = ((DatetimeParameter) param).getFormattedDate();
                break;
            case CardOrAccountParameter.MONEY:
                textValue = CurrencyHelper.formatAmount(
                        ctx,
                        ((MoneyParameter) param).getValue().getAmount(),
                        ((MoneyParameter) param).getValue().getCurrency(),
                        R.style.full_detail_item_value,
                        R.style.full_detail_currency_value);
                break;
            case CardOrAccountParameter.STRING:
                textValue = ((StringParameter) param).getValue();
                break;
            case CardOrAccountParameter.STRUCTURE:
                for (CardOrAccountParameter p : ((StructureParameter) param).getContent()) {
                    addParam(ctx, p, parametersLayout);
                }
                break;
        }
        if (!textValue.toString().isEmpty()) {
            ((TextView) v.findViewById(R.id.item_value)).setText(textValue);
            parametersLayout.addView(v);
        }
    }

    private static void fillParamMap(Map<String, CardOrAccountParameter> map, List<CardOrAccountParameter> list, String prefix) {
        for (CardOrAccountParameter param : list) {
            map.put(prefix == null ? param.getId() : prefix + param.getId(), param);

            if (CardOrAccountParameter.STRUCTURE.equals(param.getType())) {
                fillParamMap(map,
                        ((StructureParameter) param).getContent(),
                        (prefix == null ? param.getId() : prefix + param.getId()) + ".");
            }
        }
    }
}
