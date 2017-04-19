package com.socc.android.soccapp.study;

import com.socc.android.soccapp.base.BasePresenter;
import com.socc.android.soccapp.base.BaseView;

import java.util.ArrayList;

/**
 * Created by ksang on 2017-04-01.
 */
public interface StudyContract {

    interface View extends BaseView<Presenter> {
        //뷰에서 진행할 메소드 정의.
        void setLoadingIndicator(boolean active);
        void setPresenter(StudyContract.Presenter presenter);
        void showStudy(ArrayList<Study> studies);
        void attemptSignUp();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);
        void loadStudys();
    }
}
