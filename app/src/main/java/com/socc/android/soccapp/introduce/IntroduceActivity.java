package com.socc.android.soccapp.introduce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.study.StudyActivity;
import com.socc.android.soccapp.utills.SharePreferenceUtils;
import com.socc.android.soccapp.utills.customview.CircleAnimIndicator;

/**
 * Created by ksang on 2017-03-20.
 */
public class IntroduceActivity extends AppCompatActivity {
    ViewPager vp;
    private CircleAnimIndicator circleAnimIndicator;
    private SharePreferenceUtils mSharePreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        vp = (ViewPager)findViewById(R.id.intro_vpager);
        circleAnimIndicator = (CircleAnimIndicator) findViewById(R.id.circleAnimIndicator);
        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.addOnPageChangeListener(mOnPageChangeListener);
        vp.setOffscreenPageLimit(0);
        circleAnimIndicator.setItemMargin(15);
        mSharePreferenceUtils = new SharePreferenceUtils(this);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.createDotPanel(3, R.mipmap.indicator_on , R.mipmap.indicator_off);

        vp.setCurrentItem(0);

    }

    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);
        }
    };
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.i("스크롤드",Integer.toString(position)+','+Integer.toString(positionOffsetPixels)+','+Float.toString(positionOffset));

        }

        @Override
        public void onPageSelected(int position) {
            circleAnimIndicator.selectDot(position);
            if(position ==3){
                mSharePreferenceUtils.put("Intro",true);
                finish();
                Intent intent=new Intent(getApplicationContext(),StudyActivity.class);

                startActivity(intent);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.i("스테이트",Integer.toString(state));
        }
    };


    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new IntroduceFragment_1();
                case 1:
                    return new IntroduceFragment_2();
                case 2:
                    return new IntroduceFragment_3();
                case 3:
                    return new IntroduceFragment_4();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 4;
        }
    }

}
