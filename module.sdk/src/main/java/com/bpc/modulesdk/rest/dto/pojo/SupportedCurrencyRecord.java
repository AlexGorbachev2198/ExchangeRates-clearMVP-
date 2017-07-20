package com.bpc.modulesdk.rest.dto.pojo;

/**
 * Created by Smolyaninov on 18.01.2017.
 */

public class SupportedCurrencyRecord {

    private int numCode;
    private String symCode;
    private int decimal;
    private String description;


    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getSymCode() {
        return symCode;
    }

    public void setSymCode(String symCode) {
        this.symCode = symCode;
    }

    public int getDecimal() {
        return decimal;
    }

    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static SupportedCurrencyRecord newInstanceRub() {
        SupportedCurrencyRecord currency = new SupportedCurrencyRecord();
        currency.setDescription("Russian rouble");
        currency.setSymCode("RUB");
        currency.setDecimal(2);
        currency.setNumCode(643);
        return currency;
    }

    public static SupportedCurrencyRecord newInstanceEur() {
        SupportedCurrencyRecord currency = new SupportedCurrencyRecord();
        currency.setDescription("euro");
        currency.setSymCode("EUR");
        currency.setDecimal(2);
        currency.setNumCode(978);
        return currency;
    }

    public static SupportedCurrencyRecord newInstanceUsd() {
        SupportedCurrencyRecord currency = new SupportedCurrencyRecord();
        currency.setDescription("dollar");
        currency.setSymCode("USD");
        currency.setDecimal(2);
        currency.setNumCode(840);
        return currency;
    }


}
