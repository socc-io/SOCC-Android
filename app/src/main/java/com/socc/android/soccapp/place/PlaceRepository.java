package com.socc.android.soccapp.place;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AppCreater01 on 2017-04-17.
 */
public class PlaceRepository implements PlacesDataSource {
    private static PlaceRepository INSTANCE = null;
    public final PlacesDataSource mPlaceRemoteDataSource;
    Map<String, Place> mCachedPlaces; // 해쉬 맵 형태로 플레이스 값을 저장.
    boolean mCacheIsDirty = false; //캐쉬 데이터 변화 있는지 체크 하는 플레그

    public PlaceRepository(@NonNull PlacesDataSource placeRemoteDataSource) {
        mPlaceRemoteDataSource = placeRemoteDataSource;
    }
    public static PlaceRepository getInstance(PlacesDataSource placeRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new PlaceRepository(placeRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void getPlaces(@NonNull final  LoadPlacesCallback callback) {
        if (mCachedPlaces != null && !mCacheIsDirty) { //캐쉬 데이터가 있을경우.
            callback.getPlaceList(new ArrayList<>(mCachedPlaces.values()));
            return;
        }
        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            // getTasksFromRemoteDataSource(callback); 리모트 데이터를 가져와.
        } else {
            ArrayList<Place> places = new ArrayList<>();
            places.add(new Place("1","강남 돈까스","회의 종료후 돈까스 꿀맛",new LatLng(37.5919172,127.0565463),'1',"3000","http://122.45.109.21:3080/Socc/bob01.jpg","2017-04-01",(float)3.1));
            places.add(new Place("2","강남 어디선가","회의 종료후 카페 어디선가 우리는 회의중",new LatLng(37.5899532,127.0574153),'1',"3000","http://122.45.109.21:3080/Socc/bob02.jpg","2017-04-03",(float)2.1));
            places.add(new Place("3","강남 스터디룸","회의날 00님의 생일 축하 파티",new LatLng(37.5888992,127.0555593),'1',"3000","http://122.45.109.21:3080/Socc/bob03.jpg","2017-04-05",(float)4.8));
            places.add(new Place("4","강남 자연별곡","회의 종료후 비싼 자연별곡에서 !",new LatLng(37.5885592,127.0574473),'1',"3000","http://122.45.109.21:3080/Socc/bob04.jpg","2017-04-07",(float)3.5));

            refreshCache(places);
            callback.getPlaceList(new ArrayList<>(mCachedPlaces.values()));
        }

    }

    @Override
    public void addPlaceGrade(@NonNull String placeId, Float grade, @NonNull AttempPlaceGradeCallback callback) {
        Place cachedPlace = getPlaceWithId(placeId);
        if (cachedPlace != null) {
            cachedPlace.setGrage(cachedPlace.getGrade()+grade/5); //이건 이제 서버에서 해야 겠죠. cnt 개념.
            //리모트 서버에ㅓ 다시 새롭게 가져옵시다. 데이터를.
            callback.GradeResult(cachedPlace);
            return;
        }else{
            // callback.onDataNotAvailable();
        }
    }

    @Override
    public void getPlace(@NonNull String placeId, @NonNull LoadPlaceCallback callback) {

        Place cachedPlace = getPlaceWithId(placeId);

        if (cachedPlace != null) {
            callback.getPlace(cachedPlace);
            return;
        }else{
            callback.onDataNotAvailable();
        }
        //널일 경우  remotr 에서 찾아오게끔 해야지

    }

    private void refreshCache(ArrayList<Place> places) {
        if (mCachedPlaces == null) {
            mCachedPlaces = new LinkedHashMap<>();
        }
        mCachedPlaces.clear();
        for (Place place : places) {
            mCachedPlaces.put(place.getId(), place);
        }
        mCacheIsDirty = false;
    }

    @Nullable
    private Place getPlaceWithId(@NonNull String id) {

        if (mCachedPlaces == null || mCachedPlaces.isEmpty()) {
            return null;
        } else {
            return mCachedPlaces.get(id);
        }
    }

}
