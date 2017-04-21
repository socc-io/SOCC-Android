package com.socc.android.soccapp.signaccount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.AccountFragment;
import com.socc.android.soccapp.account.AccountPresenter;
import com.socc.android.soccapp.account.AccountRemoteDataSource;
import com.socc.android.soccapp.account.AccountRepository;
import com.socc.android.soccapp.network.AccountAPIService;
import com.socc.android.soccapp.utills.ActivityUtils;

import butterknife.ButterKnife;

/**
 * Created by ksang on 2017-03-22.
 */
public class SignUpActivity extends AppCompatActivity{
    private SignUpPresenter mSignUpPresenter;
    private AccountRemoteDataSource mAccountRemoteDataSource;
    private AccountRepository mAccountRepository;
    private AccountAPIService mAccountAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); //레이아웃 달아주시고.
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SignUpFragment signupFragment =(SignUpFragment)
                getSupportFragmentManager().findFragmentById(R.id.singupFrame);

        if (signupFragment == null) {
            signupFragment = SignUpFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), signupFragment, R.id.singupFrame);
        }

        mAccountAPIService = new AccountAPIService(this);
        mAccountRemoteDataSource = new AccountRemoteDataSource(mAccountAPIService);
        mAccountRepository = new AccountRepository(mAccountRemoteDataSource);
        mSignUpPresenter = new SignUpPresenter(mAccountRepository,signupFragment,this);
    }
}
