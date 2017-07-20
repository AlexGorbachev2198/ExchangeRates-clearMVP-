package com.example.red_ragnar.testing;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bpc.modulesdk.rest.dto.pojo.RateInformation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Testing.NewFirstChoice;


/**
 * Created by Red_Ragnar on 14.07.2017.
 */
public class Presenter implements IPresenter {
    Model _model;
    IView _view;
    List<RateInformation> _rsp;

    public Map makeAMap(String rates) {
        Map data = new HashMap<String, String>();
        for (int i = 0; i < _rsp.size(); i++) {
            if (_rsp.get(i).getName() == rates) {
                data.put("base", _rsp.get(i).getBase());
                data.put("buy", _rsp.get(i).getBuy());
                data.put("sell", _rsp.get(i).getSell());
                break;
            }
        }
        if (data.size() == 0) {
            data.put("base", "No data");
            data.put("buy", "No data");
            data.put("sell", "No data");
        }
        return data;
    }

    public Presenter(IView actionView) {
        _model = Model.getInstance();
        _rsp = _model.get_data();
        _view = actionView;
    }

    public Map Get_Data(String from, String to) {
        _rsp = _model.get_data();
        return makeAMap(from + "/" + to);
    }

    public List<RateInformation> GetAll_Data() {
        _rsp = _model.get_data();
        return _rsp;
    }

    public boolean GetSuccess() {
        return _model.getSuccess();
    }

    public void FromButtonClick() {
        String currentRate = _view.GetUsdFrom();
        switch (currentRate) {
            case "USD":
                _view.ChangePicFromButton("EUR");
                _view.SetUsdFrom("EUR");
                break;
            case "EUR":
                _view.ChangePicFromButton("RUR");
                _view.SetUsdFrom("RUR");
                break;
            case "RUR":
                _view.ChangePicFromButton("USD");
                _view.SetUsdFrom("USD");
                break;
        }
        ChangeViewRates();
    }

    public void ToButtonClick() {
        String currentRate = _view.GetUsdTo();
        switch (currentRate) {
            case "USD":
                _view.ChangePicToButton("EUR");
                _view.SetUsdTo("EUR");
                break;
            case "EUR":
                _view.ChangePicToButton("RUR");
                _view.SetUsdTo("RUR");
                break;
            case "RUR":
                _view.ChangePicToButton("USD");
                _view.SetUsdTo("USD");
                break;
        }
        ChangeViewRates();
    }

    public void ChangeViewRates() {
        String buff;
        if (GetSuccess()) {
            Map result = makeAMap(_view.GetUsdFrom() + "/" + _view.GetUsdTo());
            buff = "Base" + ": " + result.get("base") + "\n" + "Buy" + ": " + result.get("buy") + "\n" + "Sell" + ": " + result.get("sell") + "\n";
        } else buff = "Error : Connection failure";
        _view.SetText(buff);
    }

    public void OnViewCreate() {
        String buff = "";
        if(GetSuccess()){
            for (int i = 0; i < _rsp.size(); i++) {
                buff += _rsp.get(i).getName() + " " + _rsp.get(i).getBase() + " " + _rsp.get(i).getBuy() + " " + _rsp.get(i).getSell() + "\n";
            }
        }
        else buff = "Error: Connection failure";
        _view.SetText(buff);
    }
}