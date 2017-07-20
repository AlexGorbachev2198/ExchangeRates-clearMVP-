package com.bpc.modulesdk.net.cache;

/**
 * Created by Samoylov on 09.11.2016.
 */

public class WebRequestCacheFactory {

    public static WebRequestCache instantiate() {
        return new StaticWebRequestCache();
    }
}
