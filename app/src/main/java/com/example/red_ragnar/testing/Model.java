package com.example.red_ragnar.testing;

import com.bpc.modulesdk.rest.RestServiceFactory;
import com.bpc.modulesdk.rest.dto.pojo.RateInformation;
import com.bpc.modulesdk.rest.dto.response.RatesResponse;
import rx.subscriptions.CompositeSubscription;
import java.util.List;
import rx.Observable;

/**
 * Created by Red_Ragnar on 14.07.2017.
 */

public class Model implements IModel {
    private static Model instance;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private CompositeSubscription _compositeSubscription = new CompositeSubscription();
    private List<RateInformation> _data;
    private boolean _success;

    Model() {
        _success = false;
        Observable<RatesResponse> o = RestServiceFactory.get().getRates();
        _compositeSubscription.add(o.subscribe(this::handleResponse, this::OnError));
    }

    public List<RateInformation> get_data() {
        //Get_Rates();
        if (_success) return _data;
        else return null;
    }

    public boolean getSuccess() {
        return _success;
    }

    public void Get_Rates() {
        Observable<RatesResponse> o = RestServiceFactory.get().getRates();
        _compositeSubscription.add(o.subscribe(this::handleResponse, this::OnError));
    }

    public void handleResponse(RatesResponse ratesResponse) {
        if (ratesResponse.isSuccess()) {
            _data = ratesResponse.getRates();
            _success = true;
        } else {
            _success = false;
        }
    }

    public void OnError(Throwable throwable) {
    }
}
