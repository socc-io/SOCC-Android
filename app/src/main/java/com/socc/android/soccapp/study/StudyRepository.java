package com.socc.android.soccapp.study;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by AppCreater01 on 2017-04-02.
 */
public class StudyRepository implements StudiesDataSource {
    private static StudyRepository INSTANCE = null;

    public final StudiesDataSource mStudiesRemoteDataSource;
    Map<String, Study> mCachedStudies;
    boolean mCacheIsDirty = false;

    public StudyRepository(@NonNull StudiesDataSource studiesRemoteDataSource) {
        mStudiesRemoteDataSource = studiesRemoteDataSource;
    }
    public static StudyRepository getInstance(StudiesDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new StudyRepository(tasksRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getStudies(@NonNull LoadStudiesCallback callback) {
        if(mCachedStudies !=null && mCacheIsDirty){
            callback.onStudiesLoaded(new ArrayList<>(mCachedStudies.values()));
            return;
        }
        //if(mCacheIsDirty){
            getStudiesFromRemoteDataSource(callback);
        //}
    }
    private void getStudiesFromRemoteDataSource(@NonNull final LoadStudiesCallback callback) {
        mStudiesRemoteDataSource.getStudies(new LoadStudiesCallback() {
            @Override
            public void onStudiesLoaded(ArrayList<Study> studies) {
               // refreshCache(tasks);
                //refreshLocalDataSource(tasks);
                callback.onStudiesLoaded(new ArrayList<>(mCachedStudies.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
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
