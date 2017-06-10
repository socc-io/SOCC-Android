package com.socc.android.soccapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.AccountActivity;
import com.socc.android.soccapp.place.PlaceActivity;
import com.socc.android.soccapp.study.StudyActivity;
import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.SharePreferenceUtils;
import com.socc.android.soccapp.utills.customview.EndDrawerToggle;
import com.socc.android.soccapp.utills.customview.ProfileView;
import com.socc.android.soccapp.whoami.WhoAmIActivity;
import com.squareup.picasso.Picasso;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ksang on 2017-05-20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)  DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)  Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private int mLayoutRes;
    protected Context mContext;
    public ImageView pf_img;
    public TextView pf_title;
    public ImageView profile;
    public ProfileView fv ;
    public EndDrawerToggle mDrawerToggle;
    private SharePreferenceUtils mSharePreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        mSharePreferenceUtils = new SharePreferenceUtils(getApplicationContext());
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mLayoutRes = layoutResID;
        initToolbar();
        initDrawer();

        if(navigationView !=null){
            setupDrawerContent(navigationView);
        }

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    private void initToolbar() {
     //   toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.bringToFront();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void initDrawer(){
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
    }
    private void setupDrawerContent(NavigationView navigationView) {

        profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_profile);
        pf_title = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);
        fv = (ProfileView) navigationView.getHeaderView(0).findViewById(R.id.nav_frame);

        Picasso.with(this).load(R.drawable.profile)
                .transform(new CropCircleTransformation())
                .into(profile);
        String user_name = mSharePreferenceUtils.getValue("email","");
        pf_title.setText(user_name);


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_camera:
                                Intent intent1 =
                                        new Intent(mContext, StudyActivity.class);
                                startActivity(intent1);
                                finish();
                                break;

                            case R.id.nav_gallery:
                                Intent intent =
                                        new Intent(mContext, PlaceActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case R.id.nav_slideshow:
                                Intent intent2 =
                                        new Intent(mContext, WhoAmIActivity.class);
                                startActivity(intent2);
                                finish();
                                break;
                            case R.id.log_out:
                                mSharePreferenceUtils.put("autosignin",false);
                                Intent intent3 =
                                        new Intent(mContext, AccountActivity.class);
                                startActivity(intent3);
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onResume() {
        super.onResume();
        DLogUtils.i("Resume");
    }
    @Override
    public void onDestroy(){
        DLogUtils.i("Destroy");
        super.onDestroy();
    }



}
