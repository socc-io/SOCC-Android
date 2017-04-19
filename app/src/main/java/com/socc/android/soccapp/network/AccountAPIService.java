package com.socc.android.soccapp.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.utills.JsonResultVoUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ksang on 2017-03-17.
 */
public class AccountAPIService {
    Retrofit mRetrofit;
    APIService mApiService;
    Call<JsonResultVoUtils> mAccount;
    final String[] result = {null};
    Context con;
    ProgressDialog mDlg;


    public AccountAPIService(Context con) {
        this.con = con;
        mRetrofit = new Retrofit.Builder().baseUrl(APIService.API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        mApiService = mRetrofit.create(APIService.class);
        mDlg = new ProgressDialog(con);
        mDlg.setProgressStyle(ProgressDialog.THEME_HOLO_DARK);
        mDlg.setMessage("로그인 시도중..");

    }

    public interface SignServiceCallback<T> {

        void onSuccess(T account);
        void onFailed(String msg);
        void onError();
    }

    public void attempSignIn(Account account,final SignServiceCallback<JsonResultVoUtils> callback){
        mAccount = mApiService.getPostSignin(account);

        mDlg.show();

        mAccount.enqueue(new Callback<JsonResultVoUtils> (){
            @Override
            public void onResponse(Call<JsonResultVoUtils> call, Response<JsonResultVoUtils> response) {
                //데이터가 받아졌을 때,
                mDlg.dismiss();
                if(response.isSuccessful()){
                   // ResponseBody responseBody = (ResponseBody) response.body();
                    //Log.i("도착", responseBody.source());
                    if(response.body().getSuccess() ==1){
                        callback.onSuccess(response.body());
                    }else if(response.body().getSuccess()==0){
                        callback.onFailed(response.body().getMsg());
                    }
                    //callback.onLoaded(response.body().getSuccess());
                }

            }

            @Override
            public void onFailure(Call<JsonResultVoUtils> call, Throwable t) {
                callback.onError();
                //데이터 받기 실패시
                //Log.i("실패", t.toString());
            }
        });

    }

}
