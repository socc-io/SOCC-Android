package com.socc.android.soccapp.account;


import android.content.Context;
import android.support.annotation.NonNull;

import com.socc.android.soccapp.base.BasePresenter;
import com.socc.android.soccapp.base.BaseView;

/**
 * Created by ksang on 2017-03-16.
 */
public interface AccountContract {
    interface View extends BaseView<Presenter> {
        //뷰에서 진행할 메소드 정의.
        void setLoadingIndicator(boolean active);
        void successSignIn();
        void failedSignIn(String msg);
        void attemptSignIn(Account account);
        void attemptSignUp();
        Context getContexted();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

      //  void loadTasks(boolean forceUpdate);

        void attendLogin(@NonNull Account account);

    }
}
