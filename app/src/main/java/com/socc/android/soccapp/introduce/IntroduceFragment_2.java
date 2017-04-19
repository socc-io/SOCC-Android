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
public class IntroduceFragment_2 extends Fragment {



    public IntroduceFragment_2() {
        // 프래그먼트 생성자.
    }

    public static IntroduceFragment_2 newInstance() {
        return new IntroduceFragment_2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.frag_intro02, container, false);
        return layout;
    }

}
