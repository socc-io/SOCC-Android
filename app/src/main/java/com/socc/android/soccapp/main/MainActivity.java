package com.socc.android.soccapp.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.utills.customview.ProfileView;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ksang on 2017-03-20.
 */
public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    public ImageView profile;
    public ProfileView fv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.ic_menu_camera);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if(navigationView !=null){
            setupDrawerContent(navigationView);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {

        profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_profile);
        fv = (ProfileView) navigationView.getHeaderView(0).findViewById(R.id.nav_frame);

        Picasso.with(this).load(R.drawable.profile)
                .transform(new CropCircleTransformation())
                .into(profile);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }
}