package com.example.red_ragnar.testing;

import com.bpc.modulesdk.rest.RestServiceFactory;
import com.bpc.modulesdk.rest.dto.pojo.RateInformation;
import com.bpc.modulesdk.rest.dto.response.RatesResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by Red_Ragnar on 14.07.2017.
 */

interface IModel {
    void handleResponse(RatesResponse ratesResponse);
    void OnError(Throwable throwable);
    List<RateInformation> get_data();
}
