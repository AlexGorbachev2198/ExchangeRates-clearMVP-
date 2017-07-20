package com.bpc.modulesdk.rest.dto.pojo;

/**
 * Created by Samoylov on 31.01.2017.
 */

public class Device {

    String devid;
    String devname;
    String confirmDate;

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }
}
