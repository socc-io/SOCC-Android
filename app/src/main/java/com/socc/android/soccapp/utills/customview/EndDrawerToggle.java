package com.socc.android.soccapp.utills.customview;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.socc.android.soccapp.R;

/**
 * Created by AppCreater01 on 2017-04-09.
 */
public class EndDrawerToggle implements DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;
    private DrawerArrowDrawable arrowDrawable;
    private ImageButton toggleButton;
    private String openDrawerContentDesc;
    private String closeDrawerContentDesc;

    public EndDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar,
                           int openDrawerContentDescRes, int closeDrawerContentDescRes) {

        this.drawerLayout = drawerLayout;
        this.openDrawerContentDesc = activity.getString(openDrawerContentDescRes);
        this.closeDrawerContentDesc = activity.getString(closeDrawerContentDescRes);

        arrowDrawable = new DrawerArrowDrawable(activity);
        arrowDrawable.setDirection(DrawerArrowDrawable.ARROW_DIRECTION_END);

        //toggleButton = new ImageButton(activity, null, R.drawable.history01);
        toggleButton = new ImageButton(activity, null,R.attr.toolbarNavigationButtonStyle);
        //toggleButton.setBackgroundResource(R.drawable.ic_menu_white_18dp);
       // toggleButton.setImageDrawable(R.drawable.ic_menu_white_18dp);

        toolbar.addView(toggleButton, new ActionBar.LayoutParams(GravityCompat.END));
       // toggleButton.setImageDrawable(arrowDrawable);

        Drawable drawable = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.ic_menu_white_24dp, activity.getTheme());
        toggleButton.setImageDrawable(drawable);

        toggleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    public void syncState() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            setPosition(1f);
        }
        else {
            setPosition(0f);
        }
    }

    public void toggle() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        else {
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }

    public void setPosition(float position) {
        if (position == 1f) {
            arrowDrawable.setVerticalMirror(true);
            toggleButton.setContentDescription(closeDrawerContentDesc);
        }
        else if (position == 0f) {
            arrowDrawable.setVerticalMirror(false);
            toggleButton.setContentDescription(openDrawerContentDesc);
        }
        arrowDrawable.setProgress(position);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        setPosition(Math.min(1f, Math.max(0, slideOffset)));
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        setPosition(1f);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        setPosition(0f);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }
}