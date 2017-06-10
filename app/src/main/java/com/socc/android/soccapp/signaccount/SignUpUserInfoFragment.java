package com.socc.android.soccapp.signaccount;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.RegexUtils;
import com.socc.android.soccapp.utills.customview.RoundedTransformation;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ksang on 2017-04-20.
 */
public class SignUpUserInfoFragment extends Fragment {
    private DataManager.Datapasenger dataManager;
   // private DataManager.finished_listener
    private String mProfileUrl;

    @BindView(R.id.userinfo_image)
    ImageView profile_imgs;
    @BindView(R.id.signup_til_name)
    TextInputLayout til_name;
    @BindView(R.id.signup_til_email)
    TextInputLayout til_email;
    @BindView(R.id.signup_til_pwd)
    TextInputLayout til_pwd;
    @BindView(R.id.signup_til_repwd)
    TextInputLayout til_repwd;
    @BindView(R.id.input_name)
    EditText mName;
    @BindView(R.id.input_email)
    EditText mEmail;
    @BindView(R.id.input_password)
    EditText mPwd;
    @BindView(R.id.input_reEnterPassword)
    EditText mRepwd;



    public SignUpUserInfoFragment() {
        // 프래그먼트 생성자.
    }

    public static SignUpUserInfoFragment newInstance() {
        return new SignUpUserInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_signup_userinfo, container, false);
        ButterKnife.bind(this, root);
        DLogUtils.i("뷰");

        setHasOptionsMenu(false);
        return root;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DLogUtils.i("여기걸림!!");
        if (context instanceof DataManager.Datapasenger) {
            dataManager = (DataManager.Datapasenger) context;
            dataManager.setFinishListener(new DataManager.finished_listener() {
                @Override
                public Account onFinish() {
                    boolean passToken = true;
                    if(mName.getText().toString() !=null && mName.getText().toString().equals("")){
                        til_name.setError("이름을 입력해주세요.");
                        passToken = false;
                    }else{
                        //정규식에 걸렸으니...안되ㅣ..특수문자는..ㅠㅠ
                        if(RegexUtils.CheckSpecial(mName.getText().toString())){
                            til_name.setError("특수문자 사용이 불가능합니다.");
                            passToken = false;
                        }else{
                            passToken = true;
                            til_name.setError(null);
                        }
                    }//엔끝

                    if(mEmail.getText().toString()!=null && mEmail.getText().toString().equals("")){
                        til_email.setError("이메일을 입력해주세요.");
                        passToken = false;
                    }else{
                        if(!RegexUtils.CheckEmail(mEmail.getText().toString())) {
                            til_email.setError("이메일 양식을 지켜주세요.");
                            passToken = false;
                        }else{
                            passToken = true;
                            til_email.setError(null);
                        }
                    }//얘또 끝.

                    if(mPwd.getText().toString()!=null && mPwd.getText().toString().equals("")){
                        til_pwd.setError("비밀번호를 입력해주세요.");
                        passToken = false;
                    }else{
                        if(!RegexUtils.CheckPwd(mPwd.getText().toString())) {
                            til_pwd.setError("비밀번호 양식을 지켜주세요.");
                            passToken = false;
                        }else{
                            passToken = true;
                            til_pwd.setError(null);
                        }
                    }

                    if(mRepwd.getText().toString()!=null &&mRepwd.getText().toString().equals("")){
                        til_repwd.setError("비밀번호 재확인을 입력해주세요.");
                        passToken = false;
                    }else{
                        if(!mRepwd.getText().toString().equals(mPwd.getText().toString())){
                            til_repwd.setError("비밀번호가 틀렸습니다.");
                            passToken = false;
                        }else{
                            passToken = true;
                            til_repwd.setError(null);
                        }
                    }

                    if(passToken){
                        Account account = new Account(mEmail.getText().toString(),mPwd.getText().toString(),mName.getText().toString());
                        return account;
                    }else {
                        return null;

                    }
                }
            });
        } else {
            throw new IllegalStateException("Activity must implement DataManager interface!");
        }
    }

    public void onSomeThingClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }
    @Override
    public  void onResume(){
        super.onResume();
        mProfileUrl = dataManager.getProfileUrl();

        if(mProfileUrl !=null && !mProfileUrl.equals("")){
            DLogUtils.i("머야 2");

            Picasso.with(this.getContext()).load(Uri.fromFile(new File(mProfileUrl))).resize(350,350)
                    .transform(new RoundedTransformation())
                    .into(profile_imgs);
        }else{
            DLogUtils.i("머야 ");
        }
    }
}
