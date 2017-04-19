package com.socc.android.soccapp.place;

import android.content.Context;
import android.support.annotation.NonNull;

import com.socc.android.soccapp.account.Account;

import java.util.ArrayList;

/**
 * Created by AppCreater01 on 2017-04-14.
 */
public class PlacePresenter implements PlaceContract.Presenter {
    private final PlaceRepository mPlaceRepository;
    private final PlaceContract.View mPlaceView;


    //AccountRepository accountRepository, 파라미터 추가해야한다.
    public PlacePresenter(@NonNull PlaceRepository placeRepository ,@NonNull PlaceContract.View placeView, Context con) {
        mPlaceRepository = placeRepository;
        mPlaceView = placeView;
       // mSharePreferenceUtils = new SharePreferenceUtils(con);
        mPlaceView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void attendLogin(@NonNull Account account) {

    }
    @Override
    public void getPlaces(){
        mPlaceRepository.getPlaces(new PlacesDataSource.LoadPlacesCallback() {
            @Override
            public void getPlaceList(ArrayList<Place> places) {
                mPlaceView.drawMarker(places);
            }
            @Override
            public void onDataNotAvailable() {

            }
        });
        //레파지토리에서 플레이스를 가져온 후 뷰로 떤져줘야함.
        //리스트를 받으면 레포지토리에서 저걸 받아야 한다..!

    }

    public void getPlace(String  id){
        mPlaceRepository.getPlace(id, new PlacesDataSource.LoadPlaceCallback() {
            @Override
            public void getPlace(Place place) {
                mPlaceView.drawBottomSheet(place);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    // Place place = places.get(index);
        //mPlaceView.drawBottomSheet(place);

    }

    @Override
    public void attemptGrade(String id,float grade) {
        mPlaceRepository.addPlaceGrade(id, grade, new PlacesDataSource.AttempPlaceGradeCallback() {
            @Override
            public void GradeResult(Place place) {
                mPlaceView.afterPlaceGrade(place);
            }
        });
    }

    @Override
    public void start() {

    }
}
