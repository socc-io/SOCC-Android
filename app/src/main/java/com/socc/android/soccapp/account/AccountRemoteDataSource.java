package com.socc.android.soccapp.account;

import android.support.annotation.NonNull;
import android.util.Log;

import com.socc.android.soccapp.network.AccountAPIService;
import com.socc.android.soccapp.utills.JsonResultVoUtils;

/**
 * Created by AppCreater01 on 2017-04-05.
 */
public class AccountRemoteDataSource implements AccountDataSource {

   AccountAPIService mLoginAPIService;

    public AccountRemoteDataSource(AccountAPIService loginAPIService){
        mLoginAPIService = loginAPIService;
    }


    @Override
    public void attemptSignIn(@NonNull Account account, @NonNull final LoadAccountCallback callback) {
        mLoginAPIService.attempSignIn(account,new AccountAPIService.SignServiceCallback<JsonResultVoUtils>() {
            @Override
            public void onSuccess(JsonResultVoUtils account) {
                callback.onSignIn(account);
                Log.i("AccountRemoteDataSource", "데이터 콜벡 인");
            }
            @Override
            public void onFailed(String msg) {
                callback.onSignFailed(msg);
                //Log.i("AccountRemoteDataSource", "데이터 콜벡 인");
            }
            @Override
            public void onError() {

            }
        });
      //  callback.onSignIn();
    }

    @Override
    public void getAccount(@NonNull LoadAccountCallback callback) {

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
