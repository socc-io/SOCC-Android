package com.socc.android.soccapp.account;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.introduce.IntroduceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AppCreater01 on 2017-03-16.
 */
public class AccountFragment extends Fragment implements AccountContract.View{

    private AccountContract.Presenter mPresenter;

    @BindView(R.id.account_id)  EditText mEmailText;
    @BindView(R.id.account_pwd)  EditText mPwdText;
    @BindView(R.id.login_btn)  Button btn_method;

   // private LinearLayout mTasksView;

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
    public Context getContexted(){
        return this.getContext();
    }

    @Override
    public void successSignIn() {
        Intent intent=new Intent(this.getContext(),IntroduceActivity.class);
        startActivity(intent);
        this.getActivity().finish();
    }

    @Override
    public void failedSignIn(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void attemptSignIn(Account account) {
        mPresenter.attendLogin(account);
    }

    @OnClick({R.id.login_btn})
    public void clickMethod(View view)
    {

        String email = mEmailText.getText().toString();
        String pwd = mPwdText.getText().toString();

        attemptSignIn(new Account(email,pwd));

    }

    @Override
    public void attemptSignUp() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_account, container, false);
        ButterKnife.bind(this, root);

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
