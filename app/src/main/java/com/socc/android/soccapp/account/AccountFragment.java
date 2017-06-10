package com.socc.android.soccapp.account;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.introduce.IntroduceActivity;
import com.socc.android.soccapp.main.MainActivity;

import com.socc.android.soccapp.signaccount.SignUpActivity;
import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.FontUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.grantland.widget.AutofitHelper;

/**
 * Created by AppCreater01 on 2017-03-16.
 */
public class AccountFragment extends Fragment implements AccountContract.View{

    private AccountContract.Presenter mPresenter;
    private ProgressDialog mDlg;
    @BindView(R.id.account_id)  EditText mEmailText;
    @BindView(R.id.account_pwd)  EditText mPwdText;
    @BindView(R.id.signin_btn)  Button signin_btn;
    @BindView(R.id.checkBox) CheckBox auto_signin_flag;
    @BindView(R.id.account_maintxt) TextView maintxt;
    @BindView(R.id.account_signuptxt) TextView signuptxt;

    public AccountFragment() {
        // 프래그먼트 생성자.
    }

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void successSignIn(boolean first) {

        mPresenter.setAutoSignIn(auto_signin_flag.isChecked());

        Intent intent;

        if(first){ // 처음이 아닐 경우.
            intent=new Intent(this.getContext(),MainActivity.class);
        }else{ //처음일 경우
            intent=new Intent(this.getContext(),IntroduceActivity.class);
        }

        mDlg.dismiss();
        startActivity(intent);
        this.getActivity().finish();

       // mDlg.dismiss();
        //ehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void failedSignIn(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
        mDlg.dismiss();
    }

    @Override
    public void errorSignIn() {
        Toast.makeText(this.getContext(), "Server is Error", Toast.LENGTH_SHORT).show();
        mDlg.dismiss();
    }

    @Override
    public void attemptSignIn(final Account account) {
        mDlg.show();
        if(auto_signin_flag.isChecked()) {
            mPresenter.RecordPwd(account.getPwd());
        }

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mPresenter.attendLogin(account);
            }
        }, 700);// 0.5초 정도 딜레이를 준 후 시작

    }

    @Override
    public void checkedAuto(boolean flag) {

        auto_signin_flag.setChecked(flag);
    }

    @Override
    public void initView(Account account) {
        mEmailText.setText(account.getEmail());
        mPwdText.setText(account.getPwd());
        auto_signin_flag.setChecked(true);
        //mDlg.show();
        this.attemptSignIn(account);
    }

    @OnClick({R.id.signin_btn,R.id.account_signuptxt})
    public void clickMethod(View view)
    {
        switch (view.getId()) {
            case R.id.signin_btn:
                String email = mEmailText.getText().toString();
                String pwd = mPwdText.getText().toString();
                attemptSignIn(new Account(email,pwd));
                break;
            case R.id.account_signuptxt:
                Intent intent=new Intent(this.getContext(),SignUpActivity.class);
                startActivity(intent);
               // this.getActivity().finish();
            default:
                break;
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDlg = new ProgressDialog(this.getContext());
        mDlg.setProgressStyle(ProgressDialog.THEME_HOLO_DARK);
        mDlg.setMessage("로그인 시도중..");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_account, container, false);
        ButterKnife.bind(this, root);

        mPresenter.init();
        DisplayMetrics metrics = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int dpi = metrics.densityDpi;
        DLogUtils.i(Integer.toString(dpi));

        Typeface bold = Typeface.createFromAsset(this.getActivity().getAssets(),"Spoqa Han Sans Bold.ttf");
        String str = maintxt.getText().toString();
        String str2 = signuptxt.getText().toString();
        SpannableStringBuilder ssb2 = new SpannableStringBuilder(str2);
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new StyleSpan(Typeface.BOLD), str.length()-1, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb2.setSpan(new ForegroundColorSpan(Color.rgb(254,90,101)),str2.length()-4,str2.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        FontUtils.setGlobalFont(this.getContext(),root);
        maintxt.setText(ssb);
        AutofitHelper.create(maintxt);
       // this.maintxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 19);

        signuptxt.setText(ssb2);
        setHasOptionsMenu(false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(AccountContract.Presenter presenter) {
      if(presenter !=null){
          mPresenter =presenter;
      }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }


}
