package com.socc.android.soccapp.account;

import android.support.annotation.NonNull;

/**
 * Created by ksang on 2017-03-17.
 */
public class AccountRepository implements AccountDataSource {
    private static AccountRepository INSTANCE = null;
    public final AccountDataSource mAccountRemoteDataSource;

    public AccountRepository(@NonNull AccountDataSource accountRemoteDataSource) {
        mAccountRemoteDataSource = accountRemoteDataSource;
    }
    public static AccountRepository getInstance(AccountDataSource accountRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new AccountRepository(accountRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void attemptSignIn(@NonNull Account account, @NonNull LoadAccountCallback callback) {
        mAccountRemoteDataSource.attemptSignIn(account,callback);
    }

    @Override
    public void getAccount(@NonNull LoadAccountCallback callback) {
     //   callback.onAccountsLoaded();
    }

    @Override
    public void getAccount(@NonNull String accountId, @NonNull GetAccountCallback callback) {

    }

    @Override
    public void saveAccount(@NonNull Account account) {

    }

    @Override
    public void refreshAccount() {

    }

    @Override
    public void deleteAllAccount() {

    }

    @Override
    public void deleteAccount(@NonNull String taskId) {

    }
}
