package com.socc.android.soccapp.signaccount;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.account.AccountContract;
import com.socc.android.soccapp.account.AccountDataSource;
import com.socc.android.soccapp.account.AccountRepository;
import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.JsonResultVoUtils;
import com.socc.android.soccapp.utills.SharePreferenceUtils;

/**
 * Created by ksang on 2017-04-20.
 */
public class SignUpPresenter implements SignUpContract.Presenter {
    private final AccountRepository mAccountRepository;
    private final SignUpContract.View mSignUpView;
    private SharePreferenceUtils mSharePreferenceUtils;

    public SignUpPresenter(@NonNull AccountRepository accountRepository,@NonNull SignUpContract.View signupView, Context con) {
        mAccountRepository = accountRepository;
        mSignUpView = signupView;
        mSharePreferenceUtils = new SharePreferenceUtils(con);
        signupView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void attendSignUp(@NonNull Account account) {
        mAccountRepository.attemptSignUp(account, new AccountDataSource.AttemptSignUpCallback() {
            @Override
            public void onSuccessSignUp(JsonResultVoUtils account) {
                Log.i("SignPresenter", "프레젠트 콜벡 오케이");
                mSignUpView.successSignUp();
            }

            @Override
            public void onFailSignUp(String msg) {
                Log.i("SignPresenter", "프레젠트 콜벡 풰일");
                mSignUpView.failedSignUp(msg);
            }
        });
    }

    @Override
    public void uploadImage(@NonNull String url) {
        mAccountRepository.uploadImage(url, new AccountDataSource.UploadImageCallback() {
            @Override
            public void onSuccessUpload(String result) {
                DLogUtils.i("오성공");
            }

            @Override
            public void onFailUpload(String msg) {
                DLogUtils.i("오실패");
            }
        });
    }

    @Override
    public void start() {

    }

}
