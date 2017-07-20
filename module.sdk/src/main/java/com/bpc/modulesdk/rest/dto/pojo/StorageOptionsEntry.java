package com.bpc.modulesdk.rest.dto.pojo;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class StorageOptionsEntry {

    public boolean cardsAllowed;
    public Card.Type[] availableCards;
    public boolean accountsAllowed;
    public Account.Type[] availableAccounts;

    public StorageOptionsEntry() {
    }

    public StorageOptionsEntry(Card.Type[] availableCards, Account.Type[] availableAccounts) {
        this.availableCards = availableCards;
        cardsAllowed = availableCards != null;

        this.availableAccounts = availableAccounts;
        accountsAllowed = availableAccounts != null;
    }

}
