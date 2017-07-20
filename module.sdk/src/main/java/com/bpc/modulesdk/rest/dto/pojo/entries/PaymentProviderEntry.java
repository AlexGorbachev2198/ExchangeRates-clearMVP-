package com.bpc.modulesdk.rest.dto.pojo.entries;

import com.bpc.modulesdk.ui.views.paramsLayout.ParameterRecord;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dzmitrystrupinski on 5/19/17.
 */

public class PaymentProviderEntry implements Serializable {
    private String pid;
    private String cid;
    private String scenario;
    private String customScenario;
    private String name;
    private String image;
    private List<ParameterRecord> params;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getCustomScenario() {
        return customScenario;
    }

    public void setCustomScenario(String customScenario) {
        this.customScenario = customScenario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ParameterRecord> getParams() {
        return params;
    }

    public void setParams(List<ParameterRecord> params) {
        this.params = params;
    }

    public void copyDataFromInfoEntry(PaymentFullRecordsInfoEntry info) {
        pid = info.getPid();
        name = info.getPaymentName();
        image = info.getPaymentImage();
        params = info.getParams();
    }
}
