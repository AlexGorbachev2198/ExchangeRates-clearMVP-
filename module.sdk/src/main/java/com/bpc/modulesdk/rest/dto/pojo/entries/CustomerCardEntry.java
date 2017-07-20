package com.bpc.modulesdk.rest.dto.pojo.entries;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by Masloed on 13.01.2017.
 */

public class CustomerCardEntry implements Serializable {


    private String deviceId;
    private String track1;
    private String track2;
    private String trackKSN;
    private String pinBlock;
    private String pinKSN;
    private String reenterPinBlock;
    private String reenterPinKSN;
    private String pan;
    private String panMask;
    private String expiryDate;
    private String cardholderName;

    @JsonIgnore
    public CustomerCardEntry(String cardNumber, String pinBlock) {
        this.pan = cardNumber;
        this.pinBlock = pinBlock;
    }

    @JsonIgnore
    public CustomerCardEntry(String cardNumber, String pinBlock, String expiryDate, String cardholderName) {
        this.pan = cardNumber;
        this.pinBlock = pinBlock;
        this.expiryDate = expiryDate;
        this.cardholderName = cardholderName;
    }

    @JsonIgnore
    public CustomerCardEntry(String cardNumber, String expiryDate, String cardholderName) {
        this.pan = cardNumber;
        this.expiryDate = expiryDate;
        this.cardholderName = cardholderName;
    }

    @JsonIgnore
    public CustomerCardEntry(CustomerCardEntry cardEntry) {
        this.deviceId = cardEntry.getDeviceId();
        this.track1 = cardEntry.getTrack1();
        this.track2 = cardEntry.getTrack2();
        this.trackKSN = cardEntry.getTrackKSN();
        this.pinBlock = cardEntry.getPinBlock();
        this.pinKSN = cardEntry.getPinKSN();
        this.reenterPinBlock = cardEntry.getReenterPinBlock();
        this.reenterPinKSN = cardEntry.getReenterPinKSN();
        this.pan = cardEntry.getPan();
        this.panMask = cardEntry.getPanMask();
        this.expiryDate = cardEntry.getExpiryDate();
        this.cardholderName = cardEntry.getCardholderName();
    }

 /*   @JsonIgnore
    public CustomerCardEntry(String deviceId, String panMask, String track2, String trackKSN, String expiryDate, String cardholderName) {
        this.deviceId = deviceId;
        this.panMask = panMask;
        this.track2 = track2;
        this.trackKSN = trackKSN;
        this.expiryDate = expiryDate;
        this.cardholderName = cardholderName;
    }*/

    public CustomerCardEntry() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTrack1() {
        return track1;
    }

    public void setTrack1(String track1) {
        this.track1 = track1;
    }

    public String getTrack2() {
        return track2;
    }

    public void setTrack2(String track2) {
        this.track2 = track2;
    }

    public String getTrackKSN() {
        return trackKSN;
    }

    public void setTrackKSN(String trackKSN) {
        this.trackKSN = trackKSN;
    }

    public String getPinBlock() {
        return pinBlock;
    }

    public void setPinBlock(String pinBlock) {
        this.pinBlock = pinBlock;
    }

    public String getPinKSN() {
        return pinKSN;
    }

    public void setPinKSN(String pinKSN) {
        this.pinKSN = pinKSN;
    }

    public String getReenterPinBlock() {
        return reenterPinBlock;
    }

    public void setReenterPinBlock(String reenterPinBlock) {
        this.reenterPinBlock = reenterPinBlock;
    }

    public String getReenterPinKSN() {
        return reenterPinKSN;
    }

    public void setReenterPinKSN(String reenterPinKSN) {
        this.reenterPinKSN = reenterPinKSN;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanMask() {
        return panMask;
    }

    public void setPanMask(String panMask) {
        this.panMask = panMask;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }
}
