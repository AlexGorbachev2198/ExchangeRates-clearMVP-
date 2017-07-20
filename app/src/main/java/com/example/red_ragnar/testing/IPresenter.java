package com.example.red_ragnar.testing;

import com.bpc.modulesdk.rest.dto.pojo.RateInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Testing.NewFirstChoice;

/**
 * Created by Red_Ragnar on 19.07.2017.
 */

public interface IPresenter {
    Map makeAMap(String rates);

    Map Get_Data(String from, String to);

    List<RateInformation> GetAll_Data();

    boolean GetSuccess();

    void FromButtonClick();

    void ToButtonClick();

    void ChangeViewRates();
}
