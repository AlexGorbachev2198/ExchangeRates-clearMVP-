package com.bpc.modulesdk.rest.dto.pojo;

import com.bpc.modulesdk.utils.DateHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatetimeParameter extends CardOrAccountParameter {

    private static final String DATE_FORMAT = "yyyy/MM/dd";

    private String format = "";//optional
    private String value = "";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return DATETIME;
    }

    @JsonIgnore
    public String getFormattedDate() {
        Date date = DateHelper.parseDateTime(value);
        if (date == null) return value == null ? "" : value;
        else
            return new SimpleDateFormat(format.isEmpty() ? DATE_FORMAT : format, Locale.US).format(date);

    }
}