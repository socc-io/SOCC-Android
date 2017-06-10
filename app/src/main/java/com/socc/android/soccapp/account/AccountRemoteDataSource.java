package com.socc.android.soccapp.account;

import android.support.annotation.NonNull;
import android.util.Log;

import com.socc.android.soccapp.network.AccountAPIService;
import com.socc.android.soccapp.utills.DLogUtils;
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
        mLoginAPIService.attempSignIn(account,new AccountAPIService.SignInServiceCallback<JsonResultVoUtils>() {
            @Override
            public void onSuccess(JsonResultVoUtils account) {
                callback.onSignIn(account);
                Log.i("AccountRemoteDataSource", "데이터 콜벡 인");
            }
            @Override
            public void onFailed(String msg) {
                callback.onSignFailed(msg);
                DLogUtils.i("실패");
                //Log.i("AccountRemoteDataSource", "데이터 콜벡 인");
            }
            @Override
            public void onError() {

                DLogUtils.i("실패");
            }
        });
      //  callback.onSignIn();
    }

    @Override
    public void attemptSignUp(@NonNull Account account, @NonNull final AttemptSignUpCallback callback) {
        mLoginAPIService.attemptSignUp(account,new AccountAPIService.SignUPServiceCallback<JsonResultVoUtils>() {

            @Override
            public void onSuccess(JsonResultVoUtils account) {
                callback.onSuccessSignUp(account);
            }

            @Override
            public void onFailed(String msg) {
                callback.onFailSignUp(msg);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void uploadImage(@NonNull String url, @NonNull final UploadImageCallback callback) {
        mLoginAPIService.uploadFile(url, new AccountAPIService.ImageUploadServiceCallback<String>() {
            @Override
            public void onSuccess(String url) {
                callback.onSuccessUpload(url);
            }

            @Override
            public void onFailed(String msg) {

            }

            @Override
            public void onError() {

            }
        });
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
