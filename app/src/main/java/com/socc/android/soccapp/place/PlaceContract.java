package com.socc.android.soccapp.place;

import android.support.annotation.NonNull;

import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.base.BasePresenter;
import com.socc.android.soccapp.base.BaseView;

import java.util.ArrayList;

/**
 * Created by AppCreater01 on 2017-04-14.
 */
public interface PlaceContract {
    interface View extends BaseView<Presenter> {
        //뷰에서 진행할 메소드 정의.
        void setLoadingIndicator(boolean active);
        void drawMarker(ArrayList<Place> places);
        void drawBottomSheet(Place place);
        void afterPlaceGrade(Place place);
    }

    interface Presenter extends BasePresenter {
        void result(int requestCode, int resultCode);
        void getPlaces(); //마커를 위한 플레이스 데이터를 전부 가져옴.
        void getPlace(String id);
        void attemptGrade(String id,float grade);
        void attendLogin(@NonNull Account account);
    }
    interface ReviewListener{
        void onReview(float stars,String placeid);
    }
}
