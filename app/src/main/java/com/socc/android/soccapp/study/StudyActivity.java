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

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.network.StudyAPIService;
import com.socc.android.soccapp.place.PlaceActivity;
import com.socc.android.soccapp.utills.ActivityUtils;
import com.socc.android.soccapp.utills.SharePreferenceUtils;
import com.socc.android.soccapp.utills.customview.EndDrawerToggle;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;


/**
 * Created by ksang on 2017-04-01.
 */
public class StudyActivity extends AppCompatActivity {

    private StudyPresenter mStudyPresenter;
    private StudyRepository mStudyRepository;
    private StudyRemoteDataSource mStudyRemoteDataSource;
    private StudyAPIService mStudyAPIService;
    private SharePreferenceUtils mSharePreferenceUtils;

    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    public ImageView pf_img;
    public TextView pf_title;
    public EndDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study); //레이아웃 달아주시고.
        ButterKnife.bind(this);

        StudyFragment studyFragment =(StudyFragment)
                getSupportFragmentManager().findFragmentById(R.id.studyFrame);
        mSharePreferenceUtils = new SharePreferenceUtils(getApplicationContext());

        if (studyFragment == null) {
            studyFragment = StudyFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), studyFragment, R.id.studyFrame);
        }

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Socc Study Planer");
        ab.setIcon(R.mipmap.socc_icon);
        //ab.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        mDrawerToggle = new EndDrawerToggle(this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


        mDrawerLayout.addDrawerListener(mDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if(navigationView !=null){
            setupDrawerContent(navigationView);
        }

        mStudyAPIService = new StudyAPIService(this);
        mStudyRemoteDataSource = new StudyRemoteDataSource(mStudyAPIService);
        mStudyRepository = new StudyRepository(mStudyRemoteDataSource);
        mStudyPresenter = new StudyPresenter(mStudyRepository,studyFragment);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    private void setupDrawerContent(NavigationView navigationView) {

        pf_img = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_profile);
        pf_title = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);

       // fv = (ProfileView) navigationView.getHeaderView(0).findViewById(R.id.nav_frame);

        Picasso.with(this).load(R.drawable.profile)
                .transform(new CropCircleTransformation())
                .into(pf_img);
        String user_name = mSharePreferenceUtils.getValue("email","");
        pf_title.setText(user_name);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_camera:
                                break;

                            case R.id.nav_gallery:
                                Intent intent =
                                        new Intent(StudyActivity.this, PlaceActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

}
