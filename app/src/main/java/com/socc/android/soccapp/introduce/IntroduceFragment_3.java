package com.socc.android.soccapp.introduce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.socc.android.soccapp.R;

/**
 * Created by AppCreater01 on 2017-03-16.
 */
public class IntroduceFragment_3 extends Fragment {



    public IntroduceFragment_3() {
        // 프래그먼트 생성자.
    }

    public static IntroduceFragment_3 newInstance() {
        return new IntroduceFragment_3();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.frag_intro03, container, false);
        return layout;
    }

}
