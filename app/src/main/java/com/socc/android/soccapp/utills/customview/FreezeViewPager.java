package com.socc.android.soccapp.utills.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ksang on 2017-05-28.
 */
public class FreezeViewPager extends ViewPager {

    public boolean melted;

    public FreezeViewPager(Context context) {
        super(context);
        freeze();
    }

    public FreezeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        freeze();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            if (melted) {
                return super.onTouchEvent(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (melted) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void freeze() {
        melted = false;
    }

    public void melt() {
        melted = true;
    }

}