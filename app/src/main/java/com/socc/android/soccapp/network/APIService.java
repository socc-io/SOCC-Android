package com.socc.android.soccapp.network;

import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.study.Study;
import com.socc.android.soccapp.utills.JsonResultVoUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by AppCreater01 on 2017-04-05.
 */

public interface APIService {
    public static final String API_URL = "http://smilu.link/";

    @POST(API_URL+"login")//api주소
    Call<JsonResultVoUtils> getPostSignin(@Body Account account); //변수이름 / postid : 안드로이드에서 보낼 변수

    @POST("/hola/KomoranController")//api주소
    Call<ArrayList<Study>> getPostStudyList(); //변수이름 / postid : 안드로이드에서 보낼 변수
}
