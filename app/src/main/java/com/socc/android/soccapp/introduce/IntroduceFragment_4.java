package com.socc.android.soccapp.introduce;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.socc.android.soccapp.R;

/**
 * Created by AppCreater01 on 2017-03-16.
 */
public class IntroduceFragment_4 extends Fragment {
    ProgressDialog loading = null;

    public IntroduceFragment_4() {
        // 프래그먼트 생성자.
    }

    public static IntroduceFragment_4 newInstance() {
        return new IntroduceFragment_4();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("크리에이트","걸림");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.frag_intro04, container, false);
        loading = new ProgressDialog(layout.getContext());
        //loading = (ProgressBar) LayoutInflater.from(layout.getContext()).inflate(R.layout.progressBar1, null);
        loading.setCancelable(true);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return layout;
    }

    @Override
    public void onStart(){

        super.onStart();
        Log.i("스타트?","실화");

    }

}
