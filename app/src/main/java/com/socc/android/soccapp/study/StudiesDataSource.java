package com.socc.android.soccapp.study;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by AppCreater01 on 2017-04-02.
 */
public interface StudiesDataSource {

    interface LoadStudiesCallback {
        void onStudiesLoaded(ArrayList<Study> studies);
        void onDataNotAvailable();
    }

    interface GetStudyCallback {
        void onStudyLoaded(Study study);
        void onDataNotAvailable();
    }

    void getStudies(@NonNull LoadStudiesCallback callback);

    void getStudy(@NonNull String studyId, @NonNull GetStudyCallback callback);

    void saveStudy(@NonNull Study study);

    void completeStudy(@NonNull Study study);

    void completeStudy(@NonNull String studyId);

    void activateStudy(@NonNull Study study);

    void activateStudy(@NonNull String studyId);

    void deleteAllStudies();

    void deleteStudy(@NonNull String taskId);
}
