package com.socc.android.soccapp.study;

import android.support.annotation.NonNull;

import com.socc.android.soccapp.network.StudyAPIService;

import java.util.ArrayList;

/**
 * Created by AppCreater01 on 2017-04-05.
 */
public class StudyRemoteDataSource implements com.socc.android.soccapp.study.StudiesDataSource {

    StudyAPIService mStudyAPIService;

    public StudyRemoteDataSource(StudyAPIService studyAPIService){
        mStudyAPIService = studyAPIService;
    }

    @Override
    public void getStudies(@NonNull final LoadStudiesCallback callback) {
        mStudyAPIService.execute( new StudyAPIService.StudyServiceCallback<ArrayList<Study>>() {
            @Override
            public void onLoaded(ArrayList<Study> studies) {
                callback.onStudiesLoaded(studies);
            }

            @Override
            public void onError() {

            }
        });


    }

    @Override
    public void getStudy(@NonNull String studyId, @NonNull GetStudyCallback callback) {

    }

    @Override
    public void saveStudy(@NonNull Study study) {

    }

    @Override
    public void completeStudy(@NonNull Study study) {

    }

    @Override
    public void completeStudy(@NonNull String studyId) {

    }

    @Override
    public void activateStudy(@NonNull Study study) {

    }

    @Override
    public void activateStudy(@NonNull String studyId) {

    }

    @Override
    public void deleteAllStudies() {

    }

    @Override
    public void deleteStudy(@NonNull String taskId) {

    }
}
