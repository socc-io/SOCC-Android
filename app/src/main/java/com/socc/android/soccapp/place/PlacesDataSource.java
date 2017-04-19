package com.socc.android.soccapp.place;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by AppCreater01 on 2017-04-16.
 */
public interface PlacesDataSource {

    interface LoadPlacesCallback {
        void getPlaceList(ArrayList<Place> places);
        void onDataNotAvailable();
    }
    interface LoadPlaceCallback {
        void getPlace(Place places);
        void onDataNotAvailable();
    }
    interface AttempPlaceGradeCallback {
        void GradeResult(Place places);
      //  void onDataNotAvailable();
    }

    void getPlaces(@NonNull LoadPlacesCallback callback);
    void addPlaceGrade(@NonNull String placeId,Float grade, @NonNull AttempPlaceGradeCallback callback);
    void getPlace(@NonNull String placeId, @NonNull LoadPlaceCallback callback);
}
