package com.bpc.modulesdk.rest.dto.request;

import com.bpc.modulesdk.rest.dto.pojo.entries.LocationEntry;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Masloed on 24.11.2016.
 */
public class LoginRequest {

    @JsonProperty("name")
    private String login;
    @JsonProperty("pwd")
    private String password;
    @JsonProperty("devId")
    private String deviceId;
    private String locale;
    private LocationEntry location;

    public LoginRequest(String login, String password, String deviceId) {
        this.login = login;
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public LocationEntry getLocation() {
        return location;
    }

    public void setLocation(LocationEntry location) {
        this.location = location;
    }
}
