package com.bpc.modulesdk.rest;

import com.bpc.modulesdk.rest.mock.MockRestService;
import com.bpc.modulesdk.rest.retrofit.RetrofitRestService;
import com.bpc.modulesdk.security.SharedPreferencesHelper;

/**
 * Created by Masloed on 24.11.2016.
 */

public class
RestServiceFactory {
    public static RestService get() {
        boolean isDemoMode = SharedPreferencesHelper.isDemoMode();
        if (isDemoMode) return MockRestService.create();
        else return RetrofitRestService.create();
    }
}
