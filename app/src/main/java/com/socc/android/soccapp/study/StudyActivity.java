package com.socc.android.soccapp.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.AccountActivity;
import com.socc.android.soccapp.base.BaseActivity;
import com.socc.android.soccapp.network.StudyAPIService;
import com.socc.android.soccapp.place.PlaceActivity;
import com.socc.android.soccapp.utills.ActivityUtils;
import com.socc.android.soccapp.utills.SharePreferenceUtils;
import com.socc.android.soccapp.utills.customview.EndDrawerToggle;
import com.socc.android.soccapp.whoami.WhoAmIActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;


/**
 * Created by ksang on 2017-04-01.
 */
public class StudyActivity extends BaseActivity {

    private StudyPresenter mStudyPresenter;
    private StudyRepository mStudyRepository;
    private StudyRemoteDataSource mStudyRemoteDataSource;
    private StudyAPIService mStudyAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_study); //레이아웃 달아주시고.
        ButterKnife.bind(this);

        StudyFragment studyFragment =(StudyFragment)
                getSupportFragmentManager().findFragmentById(R.id.studyFrame);
      //  mSharePreferenceUtils = new SharePreferenceUtils(getApplicationContext());

        if (studyFragment == null) {
            studyFragment = StudyFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), studyFragment, R.id.studyFrame);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();

        mStudyAPIService = new StudyAPIService(this);
        mStudyRemoteDataSource = new StudyRemoteDataSource(mStudyAPIService);
        mStudyRepository = new StudyRepository(mStudyRemoteDataSource);
        mStudyPresenter = new StudyPresenter(mStudyRepository,studyFragment);

    }

}
