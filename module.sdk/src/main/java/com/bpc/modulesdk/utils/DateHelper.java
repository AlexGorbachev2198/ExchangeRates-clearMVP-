package com.bpc.modulesdk.utils;

import android.support.annotation.Nullable;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {

    private static final String TAG = "DateHelper";

    /**
     * Parse date with format: yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ
     * <p/>
     * Example:
     * 2016-02-29T12:15:30.500+04:00
     * 2016-02-29T12:15:30.500Z
     *
     * @param dateTime date with format: yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ
     * @return date if everything ok
     */
    @Nullable
    public static Date parseDateTime(String dateTime) {
        if (dateTime == null || dateTime.isEmpty())
            return null;

        String datePattern;
        if (dateTime.length() == 29) datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";
        else datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

        try {
            return new SimpleDateFormat(datePattern, Locale.US).parse(dateTime);
        } catch (ParseException e) {
            Log.e(TAG, "Cannot parse date with pattern " + datePattern, e);
            return null;
        }
    }

    /**
     * Parse date with format: yyyy-MM-dd
     * <p/>
     * Example:
     * 2016-02-29
     *
     * @param date date with format: yyyy-MM-dd
     * @return date if everything ok
     */
    @Nullable
    public static Date parseDate(String date) {
        if (date == null || date.isEmpty())
            return null;

        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date);
        } catch (ParseException e) {
            Log.e(TAG, "Cannot parse date", e);
            return null;
        }
    }

    /**
     * Parse date with format: HH:mm:ss.SSSZZZZZ
     * <p/>
     * Example:
     * 12:15:30.500+04:00
     * 12:15:30.500Z
     *
     * @param time date with format: HH:mm:ss.SSSZZZZZ
     * @return date if everything ok
     */
    @Nullable
    public static Date parseTime(String time) {
        if (time == null || time.isEmpty())
            return null;

        String datePattern;
        if (time.length() == 18) datePattern = "HH:mm:ss.SSSZZZZZ";
        else datePattern = "HH:mm:ss.SSS'Z'";

        try {
            return new SimpleDateFormat(datePattern, Locale.US).parse(time);
        } catch (ParseException e) {
            Log.e(TAG, "Cannot parse date pattern " + datePattern, e);
            return null;
        }
    }

    @Nullable
    public static String formatDateTime(Date date) {
        if (date == null)
            return null;
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.US).format(date);
    }

    @Nullable
    public static String formatDate(Date date) {
        if (date == null)
            return null;
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date);
    }

    @Nullable
    public static String formatTime(Date date) {
        if (date == null)
            return null;
        return new SimpleDateFormat("HH:mm:ss.SSSZZZZZ", Locale.US).format(date);
    }

    @Nullable
    public static String formatDateWithTimeZone(DateFormat dateFormat, Date date) {
        if (dateFormat == null || date == null)
            return null;

        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(date);
    }

    public static boolean isContainTime(String datePattern) {
        if (datePattern == null)
            return false;
        String lowCaseDatePattern = datePattern.toLowerCase();
        return lowCaseDatePattern.contains("h") || lowCaseDatePattern.contains("k");
    }

    public static Calendar parseCardExpiryDate(String dateToCompare) {
        Calendar dateToComp = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy", Locale.US);
        try {
            dateToComp.setTime(sdf.parse(dateToCompare));
            dateToComp.set(Calendar.DATE, dateToComp.getActualMaximum(Calendar.DAY_OF_MONTH));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateToComp;
    }

    public static boolean compareCardExpDateLexicographically(String dateToCompare) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM", Locale.US);
        Date date = new Date();
        String currentDate = sdf.format(date);
        String dateToComp = sdf.format((parseCardExpiryDate(dateToCompare)).getTime());

        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if (o1.compareTo(o2) == 9) return -1;
                else if (o1.compareTo(o2) == -9) return 1;
                else if (o1.compareTo(o2) > 0) return 1;
                else if (o1.compareTo(o2) == 0) return 0;
                else return -1;
            }
        };

        return comp.compare(dateToComp, currentDate) >= 0;
    }

    public static Date getCalendarDate(Period period) {
        final Calendar calendar = Calendar.getInstance();
        if (period == Period.TODAY || period == null) {
            calendar.add(Calendar.DATE, 0);
        } else if (period == Period.DAY || period == Period.YESTERDAY) {
            calendar.add(Calendar.DATE, -1);
        } else if (period == Period.WEEK) {
            calendar.add(Calendar.DAY_OF_MONTH, -7);
        } else if (period == Period.MONTH) {
            calendar.add(Calendar.MONTH, -1);
        } else if (period == Period.YEAR) {
            calendar.add(Calendar.YEAR, -1);
        }

        return calendar.getTime();
    }

    public enum Period {
        DAY(1), WEEK(2), MONTH(3), YEAR(4), TODAY(5), YESTERDAY(6);

        int code;

        Period(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}