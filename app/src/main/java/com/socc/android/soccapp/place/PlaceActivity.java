package com.socc.android.soccapp.place;

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
import com.socc.android.soccapp.study.StudyActivity;
import com.socc.android.soccapp.utills.ActivityUtils;
import com.socc.android.soccapp.utills.SharePreferenceUtils;
import com.socc.android.soccapp.utills.customview.EndDrawerToggle;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by AppCreater01 on 2017-04-14.
 */
public class PlaceActivity extends AppCompatActivity {
    private SharePreferenceUtils mSharePreferenceUtils;
    private PlacePresenter mPlacePresenter;
    private PlaceRepository mPlaceRepository;
    private PlaceRemoteDataSource mPlaceRemoteDataSource;


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
        setContentView(R.layout.activity_place);
        ButterKnife.bind(this);

        PlaceFragment placeFragment =(PlaceFragment)
                getSupportFragmentManager().findFragmentById(R.id.placeFrame);

        if (placeFragment == null) {
            placeFragment = PlaceFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), placeFragment, R.id.placeFrame);
        }
       // ActivityUtils.changeFragementToAcitivity(getSupportFragmentManager(),);

        mPlaceRemoteDataSource = new PlaceRemoteDataSource();
        mPlaceRepository = new PlaceRepository(mPlaceRemoteDataSource);
        mSharePreferenceUtils = new SharePreferenceUtils(getApplicationContext());
        mPlacePresenter = new PlacePresenter(mPlaceRepository,placeFragment,this);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Socc BoB History");
        ab.setIcon(R.mipmap.socc_icon);
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


    }

    private void setupDrawerContent(NavigationView navigationView) {
        pf_img = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_profile);
        pf_title = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);

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
                                Intent intent1 =
                                        new Intent(PlaceActivity.this, StudyActivity.class);
                                startActivity(intent1);
                                finish();
                                break;

                            case R.id.nav_gallery:

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
