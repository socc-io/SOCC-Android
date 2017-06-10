package com.socc.android.soccapp.signaccount;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.utills.DLogUtils;

import butterknife.ButterKnife;

/**
 * Created by ksang on 2017-06-03.
 */
public class SignUpSuccessFragment extends Fragment {

    public SignUpSuccessFragment() {
        // 프래그먼트 생성자.
    }

    public static SignUpSuccessFragment newInstance() {
        return new SignUpSuccessFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_signup_success, container, false);

        setHasOptionsMenu(false);
        return root;
    }
}
