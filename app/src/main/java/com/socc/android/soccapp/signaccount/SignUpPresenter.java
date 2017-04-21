package com.socc.android.soccapp.signaccount;

import android.content.Context;
import android.support.annotation.NonNull;

import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.account.AccountContract;
import com.socc.android.soccapp.account.AccountRepository;
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

    }

    @Override
    public void start() {

    }
}
