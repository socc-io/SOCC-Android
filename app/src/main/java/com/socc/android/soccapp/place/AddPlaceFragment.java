package com.socc.android.soccapp.place;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socc.android.soccapp.R;

/**
 * Created by AppCreater01 on 2017-04-18.
 */
public class AddPlaceFragment extends Fragment{

    public AddPlaceFragment() {
        // 프래그먼트 생성자.
    }

    public static AddPlaceFragment newInstance() {
        return new AddPlaceFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.frag_addplace, container, false );
        return view;
    }
}
