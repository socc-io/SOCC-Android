package com.socc.android.soccapp.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.socc.android.soccapp.study.Study;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ksang on 2017-03-17.
 */
public class StudyAPIService {
    Retrofit mRetrofit;
    APIService mApiService;
    Call<ArrayList<Study>> studylist;
    final String[] result = {null};
    Context con;
    ProgressDialog mDlg;


    public StudyAPIService(Context con) {
        this.con = con;
        mRetrofit = new Retrofit.Builder().baseUrl(APIService.API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        mApiService = mRetrofit.create(APIService.class);
        mDlg = new ProgressDialog(con);
        mDlg.setProgressStyle(ProgressDialog.THEME_HOLO_DARK);
        mDlg.setMessage("Loading");

    }
    public interface SignServiceCallback<T> {

        void onLoaded(T studies);
        void onError();
    }

    public interface StudyServiceCallback<T> {

        void onLoaded(T studies);
        void onError();
    }

    public void execute(final StudyServiceCallback<ArrayList<Study>> callback){
        studylist = mApiService.getPostStudyList();

        //mDlg.show();

        studylist.enqueue(new Callback<ArrayList<Study>> (){
            @Override
            public void onResponse(Call<ArrayList<Study>> call, Response<ArrayList<Study>> response) {
                //데이터가 받아졌을 때,
                //mDlg.dismiss();
                Log.i("도착", "했데요");
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<Study>> call, Throwable t) {
                callback.onError();
                //데이터 받기 실패시
                //Log.i("실패", t.toString());
            }
        });

    }

}
