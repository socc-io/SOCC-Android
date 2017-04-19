package com.socc.android.soccapp.account;

import android.support.annotation.NonNull;

import com.socc.android.soccapp.utills.JsonResultVoUtils;

import java.util.List;

/**
 * Created by ksang on 2017-03-17.
 */
public interface  AccountDataSource {
    interface LoadAccountCallback {
        void onAccountsLoaded(List<Account> accounts);
        void onSignIn(JsonResultVoUtils account);
        void onSignFailed(String msg);
        void onDataNotAvailable();
    }

    interface GetAccountCallback {
        void onSigninResult(LoadAccountCallback callback);
        void onTaskLoaded(Account account);
        void onDataNotAvailable();
    }

    void attemptSignIn(@NonNull Account account, @NonNull LoadAccountCallback callback);

    void getAccount(@NonNull LoadAccountCallback callback);

    void getAccount(@NonNull String accountId, @NonNull GetAccountCallback callback);

    void saveAccount(@NonNull Account account);

    void refreshAccount();

    void deleteAllAccount();

    void deleteAccount(@NonNull String taskId);
}
