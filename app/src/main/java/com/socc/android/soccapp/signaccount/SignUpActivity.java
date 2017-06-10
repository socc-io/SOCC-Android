package com.socc.android.soccapp.signaccount;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.account.AccountRemoteDataSource;
import com.socc.android.soccapp.account.AccountRepository;
import com.socc.android.soccapp.network.AccountAPIService;
import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.customview.FreezeViewPager;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ksang on 2017-05-28.
 */
public class SignUpActivity extends AppCompatActivity implements  SignUpContract.View,DataManager.Datapasenger{

    @BindView(R.id.signup_vpager)
    FreezeViewPager vp;
    @BindView(R.id.step_first_text)
    TextView first_txt;
    @BindView(R.id.step_second_text)
    TextView second_txt;
    @BindView(R.id.step_third_text)
    TextView third_txt;
    @BindView(R.id.step_progress)
    ProgressBar step_bar;
    @BindView(R.id.step_btn)
    Button step_btn;
    @BindView(R.id.vpager_step_txt)
    TextView step_txt;
    @BindView(R.id.vpager_title_txt)
    TextView step_title;

    private int curr_step,layout_width;
    private boolean provision_flag = false;
    private boolean profile_flag = false;
    private boolean userinfo_flag = false;
    private Context mContext;
    private String profile_url;
    private AccountRemoteDataSource mAccountRemoteDataSource;
    private AccountRepository mAccountRepository;
    private AccountAPIService mAccountAPIService;
    private SignUpContract.Presenter mSignUpPresenter;
    private DataManager.finished_listener listener;
    private ProgressDialog mDlg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mContext = this;
        ButterKnife.bind(this);
        mAccountAPIService = new AccountAPIService();
        mAccountRemoteDataSource = new AccountRemoteDataSource(mAccountAPIService);
        mAccountRepository = new AccountRepository(mAccountRemoteDataSource);
        mSignUpPresenter = new SignUpPresenter(mAccountRepository,this,this.getBaseContext());

        //디스플레이 가로 길이 측정.
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        layout_width = size.x;

        first_txt.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
                int dp = Math.round((layout_width/4) / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                int px = Math.round(first_txt.getWidth()/2 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                int dp2 = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                RelativeLayout.LayoutParams plControl = (RelativeLayout.LayoutParams) second_txt.getLayoutParams();
                plControl.leftMargin = dp+dp2;
                second_txt.setLayoutParams(plControl);
                first_txt.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        third_txt.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
                int dp = Math.round((layout_width/4) / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                int px = Math.round(third_txt.getWidth()/2 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                int dp2 = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
                RelativeLayout.LayoutParams plControl = (RelativeLayout.LayoutParams) second_txt.getLayoutParams();
                plControl.rightMargin = dp+dp2;
                second_txt.setLayoutParams(plControl);
                third_txt.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        curr_step = 0;

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.addOnPageChangeListener(mOnPageChangeListener);
        vp.setOffscreenPageLimit(0);
        vp.setCurrentItem(0);
        vp.freeze();
        step_title.setText("약관 동의");
        step_txt.setText("STEP 01");
        first_txt.setBackgroundResource(R.drawable.step_view);

        step_bar.setProgress(25);

    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            curr_step = position;
            if(position==0){
                first_txt.setBackgroundResource(R.drawable.step_view);
                step_bar.setProgress(25);
                step_title.setText("약관 동의");
                step_txt.setText("STEP 01");
            }
            else if(position==1){
                second_txt.setBackgroundResource(R.drawable.step_view);
                step_bar.setProgress(50);
                step_title.setText("프로필 사진 등록");
                step_txt.setText("STEP 02");
            }else if(position==2){
                step_title.setText("회원 정보 기입");
                step_txt.setText("STEP 03");
                third_txt.setBackgroundResource(R.drawable.step_view);
                step_bar.setProgress(75);
            }else if(position ==3){
                step_bar.setProgress(100);
                step_title.setText("회원가입 완료");
                step_txt.setText("FINISH");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void saveProfileUrl(String url) {
        if(url ==null || url.isEmpty()){
            profile_flag = false;
            profile_url = null;
            return;
        }

        this.profile_url= url;
        profile_flag = true;
    }

    @Override
    public String getProfileUrl() {
        return this.profile_url;
    }

    @Override
    public void setFinishListener(DataManager.finished_listener listener) {
        this.listener = listener;
    }


    @Override
    public void confirmChk(boolean flag) {
        provision_flag = flag;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void successSignUp() {
        mDlg.dismiss();
        userinfo_flag=true;
        vp.setCurrentItem(curr_step+1);
    }

    @Override
    public void failedSignUp(String msg) {
        mDlg.dismiss();
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void setPresenter(SignUpContract.Presenter presenter) {
        this.mSignUpPresenter = presenter;
    }

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
                    return new SignUpProvisionFragment();
                case 1:
                    return new SignUpProfileFragment();
                case 2:
                    return new SignUpUserInfoFragment();
                case 3:
                    return new SignUpSuccessFragment();
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

    @OnClick({R.id.step_btn})
    public void onSomeThingClick(View view) {
        switch (view.getId()) {
            case R.id.step_btn:
                if(curr_step==0){
                    if(provision_flag){
                        vp.setCurrentItem(curr_step+1);
                    }else{
                        Toast.makeText(this,"체크를 해주시죠.",Toast.LENGTH_SHORT).show();
                    }
                }else if(curr_step==1){
                    if(profile_flag){
                        vp.setCurrentItem(curr_step+1);
                    }else{
                        Toast.makeText(this,"프로필 사진을 정해주시죠.",Toast.LENGTH_SHORT).show();
                    }

                }else if(curr_step==2) {
                    //프레그먼트로 부터 계정정보를 넘겨받음.
                    Account account = listener.onFinish();
                    if (account != null) {
                        mSignUpPresenter.attendSignUp(account);
                        mDlg = new ProgressDialog(mContext);
                        mDlg.setProgressStyle(ProgressDialog.THEME_HOLO_DARK);
                        mDlg.setMessage("가입 중..");
                        mDlg.show();
                    } else { //넘겨받지 못할경우..?
                        DLogUtils.i("널이래ㅠㅠ");
                    }
                }else if(curr_step==3){
                    finish();
                }
                break;

            default:
                break;

        }


    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
