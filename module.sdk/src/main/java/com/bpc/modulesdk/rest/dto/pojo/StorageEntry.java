package com.bpc.modulesdk.rest.dto.pojo;

/**
 * Created by Smolyaninov on 20.01.2017.
 */

public class StorageEntry {

    public String card;
    public String account;

    public StorageEntry(String card, String account) {
        this.card = card;
        this.account = account;
    }
}