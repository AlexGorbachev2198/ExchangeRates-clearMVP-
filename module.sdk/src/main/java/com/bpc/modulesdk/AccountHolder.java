package com.bpc.modulesdk;

import android.support.annotation.Nullable;
import android.util.Log;

import com.bpc.modulesdk.rest.RestServiceFactory;
import com.bpc.modulesdk.rest.dto.pojo.Account;
import com.bpc.modulesdk.rest.mock.MockRestService;
import com.bpc.modulesdk.rest.mock.MockRestServiceDataStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Smolyaninov on 20.01.2017.
 */

public class AccountHolder {
    private static final String TAG = "AccountHolder";

    private static AccountHolder mInstance = null;

    public static AccountHolder get() {
        if (mInstance == null) {
            mInstance = new AccountHolder();
        }
        return mInstance;
    }

    private AccountHolder() {
    }


    private List<AccountsChangeListener> accountsChangeListeners = new LinkedList<>();
    private List<Account> accounts;

    public Account getAccount(String number) {
        if (number == null || accounts == null)
            return null;
        for (Account account : accounts)
            if (number.equals(account.getNumber()))
                return account;
        return null;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccountsChangeListener(AccountsChangeListener listener) {
        if (listener != null && !accountsChangeListeners.contains(listener))
            accountsChangeListeners.add(listener);

    }

    public void removeAccountsChangeListener(AccountsChangeListener listener) {
        accountsChangeListeners.remove(listener);
    }

    Subscription subscription;
    public void update() {
        subscription = RestServiceFactory.get().getAccounts().subscribe(accountsResponse -> {
            if (accountsResponse.isSuccess()) {
                accounts = accountsResponse.getAccounts();
                Log.d("ACCOUNTS LENGTH", accounts.size()+" "+ MockRestService.create().getAccounts() +
                        " " + MockRestServiceDataStorage.getInstance().getAccounts().size());
                if (accounts != null)
                    Collections.sort(accounts, new Account.AccountTypeComparator());
                for (AccountsChangeListener listener : accountsChangeListeners)
                    listener.onAccountsChange(AccountHolder.this);
            } else {
                //TODO
                //notifyAboutError(new RequestException(accountsResponse.getDescription(), accountsResponse));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                notifyAboutError(throwable);
            }
        });
        /*WebService.getAccountsRequest()
                .subscribe(new Action1<AccountsResponse>() {
                    @Override
                    public void call(AccountsResponse accountsResponse) {
                        if (accountsResponse.isSuccess()) {
                            accounts = accountsResponse.getAccounts();
                            if (accounts != null)
                                Collections.sort(accounts, new Account.AccountTypeComparator());
                            for (AccountsChangeListener listener : accountsChangeListeners)
                                listener.onAccountsChange(AccountHolder.this);
                        } else {
                            notifyAboutError(new RequestException(accountsResponse.getDescription(), accountsResponse));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        notifyAboutError(throwable);
                    }
                });*/

    }



    @Nullable
    public List<Account> getActiveAccounts() {
        if (accounts == null)
            return null;
        List<Account> activeAccounts = new ArrayList<>(accounts.size());
        for (Account account : accounts)
            if (account.getActive() == Account.Active.YES)
                activeAccounts.add(account);
        return activeAccounts;
    }

    private void notifyAboutError(Throwable throwable) {
        for (AccountsChangeListener listener : accountsChangeListeners)
            listener.onAccountsChangeError(throwable);
    }

    public interface AccountsChangeListener {
        void onAccountsChange(AccountHolder accountHolder);

        void onAccountsChangeError(Throwable throwable);
    }


}
