package com.socc.android.soccapp.signaccount;

import android.content.Context;
import android.support.annotation.NonNull;

import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.base.BasePresenter;
import com.socc.android.soccapp.base.BaseView;

/**
 * Created by ksang on 2017-04-20.
 */
public interface SignUpContract {
    interface View extends BaseView<Presenter> {
        //뷰에서 진행할 메소드 정의.
        void setLoadingIndicator(boolean active);
        void successSignUp();
        void failedSignUp(String msg);
    }

    interface Presenter extends BasePresenter {
        void result(int requestCode, int resultCode);
        void attendSignUp(@NonNull Account account);
        void uploadImage(@NonNull String url);

    }
}
