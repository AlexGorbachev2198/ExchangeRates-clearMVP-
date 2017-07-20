package com.bpc.modulesdk.net.cache;

/**
 * Created by Samoylov on 09.11.2016.
 */
public class CacheTime {

    public static long ALWAYS_EXPIRED = 0;
    public static long MINUTE = 60 * 1000;
    public static long HOUR = 60 * 60 * 1000;
    public static long DAY = 24 * 60 * 60 * 1000;
    public static long WEEK = 7 * 24 * 60 * 60 * 1000;
    public static long ALWAYS_RETURN = Long.MAX_VALUE;
}
