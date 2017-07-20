package com.bpc.modulesdk.net.cache;

import android.support.annotation.Nullable;

import com.bpc.modulesdk.rest.dto.response.CommonResponse;


/**
 * Created by Samoylov on 09.11.2016.
 */
public interface WebRequestCache {

    <T extends CommonResponse> void add(T data, @Nullable String cacheTag);

    <T extends CommonResponse> T get(Class<T> clazz, @Nullable String cacheTag, long expirationTime);

    void remove();

    <T extends CommonResponse> void remove(Class<T> clazz);

    <T extends CommonResponse> void remove(Class<T> clazz, String cacheTag);
}