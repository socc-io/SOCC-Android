package com.socc.android.soccapp.account;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.JsonResultVoUtils;
import com.socc.android.soccapp.utills.SharePreferenceUtils;

import java.util.List;

/**
 * Created by ksang on 2017-03-16.
 */
public class AccountPresenter implements AccountContract.Presenter{
    private final AccountRepository mAccountRepository;
    private final AccountContract.View mAccountView;
    private SharePreferenceUtils mSharePreferenceUtils;

    public AccountPresenter(@NonNull AccountRepository accountRepository,@NonNull AccountContract.View accountView, Context con) {
        mAccountRepository = accountRepository;
        mAccountView = accountView;
        mSharePreferenceUtils = new SharePreferenceUtils(con);
        mAccountView.setPresenter(this);
    }

    @Override
    public void start() {
        //loadTasks(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void RecordPwd(String pwd) {
        mSharePreferenceUtils.put("pwd",pwd);
    }

    @Override
    public void setAutoSignIn(boolean flag) {
        mSharePreferenceUtils.put("autosignin",flag);
    }

    @Override
    public void init() {
        boolean autologin = mSharePreferenceUtils.getValue("autosignin",false);
        if(autologin){
            String email = mSharePreferenceUtils.getValue("email","");
            String pwd = mSharePreferenceUtils.getValue("pwd","");
            mAccountView.initView(new Account(email,pwd));
        }
    }

    @Override
    public void attendLogin(@NonNull Account account) {
        mAccountRepository.attemptSignIn(account , new AccountDataSource.LoadAccountCallback() {
            @Override
            public void onAccountsLoaded(List<Account> accounts) {

            }

            /**
             *
             * @param account
             * @result 로그인 성공 시 넘겨 오는 콜벡 함수.
             */
            @Override
            public void onSignIn(JsonResultVoUtils account) {
                mSharePreferenceUtils.put("email", account.getUser().getEmail());
                mSharePreferenceUtils.put("roll",account.getUser().getRole());
                mAccountView.checkedAuto(true);
                mAccountView.successSignIn(mSharePreferenceUtils.getValue("Intro",false));

            }

            @Override
            public void onSignFailed(String msg) {
                mAccountView.failedSignIn(msg);
            }

            @Override
            public void onSignError() {
                mAccountView.errorSignIn();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }



}
