package com.socc.android.soccapp.main;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.utills.FontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ksang on 2017-05-07.
 */
public class MainFragment extends Fragment{
    @BindView(R.id.mt_etc)
    TextView etc;
    @BindView(R.id.mt_study)
    TextView study;

    public MainFragment() {
        // 프래그먼트 생성자.
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_main_header, container, false);
        ButterKnife.bind(this, root);
        FontUtils.setMontserratBoldFont(this.getContext(),root);

        Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(), "Montserrat Bold.otf");
        study.setTypeface(face);
        etc.setTypeface(face);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
