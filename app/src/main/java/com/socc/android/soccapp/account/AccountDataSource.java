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
        void onSignError();
        void onDataNotAvailable();
    }
    interface AttemptSignUpCallback {
        void onSuccessSignUp(JsonResultVoUtils account);
        void onFailSignUp(String msg);
    }
    interface GetAccountCallback {

    }
    interface UploadImageCallback {
        void onSuccessUpload(String result);
        void onFailUpload(String msg);
    }


    void attemptSignIn(@NonNull Account account, @NonNull LoadAccountCallback callback);

    void attemptSignUp(@NonNull Account account, @NonNull AttemptSignUpCallback callback);

    void uploadImage(@NonNull String url,@NonNull UploadImageCallback callback);

    void getAccount(@NonNull LoadAccountCallback callback);

    void getAccount(@NonNull String accountId, @NonNull GetAccountCallback callback);

    void saveAccount(@NonNull Account account);

    void refreshAccount();

    void deleteAllAccount();

    void deleteAccount(@NonNull String taskId);
}
