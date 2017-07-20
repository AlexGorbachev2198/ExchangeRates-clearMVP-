package com.bpc.modulesdk.net.cache;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.bpc.modulesdk.rest.DtoObjectMapper;
import com.bpc.modulesdk.rest.dto.response.CommonResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 * Created by Samoylov on 14.11.2016.
 * <p>
 * Cache in {@link ArrayMap}, without saving any data in file system.
 */
public class StaticWebRequestCache implements WebRequestCache {

    private static final String TAG = "StaticWebRequestCache";

    private static ArrayMap<Class, ArrayMap<String, Entity>> storage = null;
    private DtoObjectMapper mapper = DtoObjectMapper.create();

    private ArrayMap<Class, ArrayMap<String, Entity>> getStorage() {
        if (storage == null)
            storage = new ArrayMap<>(100);
        return storage;
    }

    @Override
    public <T extends CommonResponse> void add(T data, @Nullable String cacheTag) {
        //Copy object for removing linking with stored object and returned to view object
        data = mapper.copyObject(data);
        if (data == null)
            return;

        Log.v(TAG, "***** CACHE add to cache. Class: " + data.getClass().getName());
        ArrayMap<String, Entity> tagMap = getStorage().get(data.getClass());
        if (tagMap == null)
            tagMap = new ArrayMap<>(10);

        tagMap.put(cacheTag, new Entity(data, System.currentTimeMillis(), cacheTag));
        getStorage().put(data.getClass(), tagMap);
    }

    @Override
    public <T extends CommonResponse> T get(Class<T> clazz, @Nullable String cacheTag, long expirationTime) {
        ArrayMap<String, Entity> tagMap = getStorage().get(clazz);
        if (tagMap == null)
            return null;
        Entity elem = tagMap.get(cacheTag);
        if (elem != null && elem.time > System.currentTimeMillis() - expirationTime) {
            Log.v(TAG, "***** CACHE return data. Class: " + clazz.getName());
            return (T) elem.data;
        }
        return null;
    }

    @Override
    public void remove() {
        Log.v(TAG, "***** CACHE remove ALL data.");
        storage = null;
    }

    @Override
    public <T extends CommonResponse> void remove(Class<T> clazz) {
        Log.v(TAG, "***** CACHE remove data. Class: " + clazz.getName());
        getStorage().put(clazz, null);
    }

    @Override
    public <T extends CommonResponse> void remove(Class<T> clazz, String cacheTag) {
        Log.v(TAG, "***** CACHE remove data by TAG. Class: " + clazz.getName());
        ArrayMap<String, Entity> tagMap = getStorage().get(clazz);
        if (tagMap != null)
            tagMap.put(cacheTag, null);
    }

    private <T extends CommonResponse> T copySerialize(T data) {
        try {
            //Serialization of object
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(data);

            //De-serialization of object
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);
            T copied = (T) in.readObject();

            return copied;
        } catch (Exception e) {
            Log.e(TAG, "Cannot copy object", e);
        }
        return null;
    }

    private class Entity<T extends CommonResponse> {
        public T data;
        public long time;

        private Entity(T data, long time, String tag) {
            this.data = data;
            this.time = time;
        }
    }
}
