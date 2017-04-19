package com.socc.android.soccapp.account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.network.AccountAPIService;
import com.socc.android.soccapp.utills.ActivityUtils;
import butterknife.ButterKnife;


/**
 * Created by ksang on 2017-03-16.
 */
public class AccountActivity extends AppCompatActivity{

    private AccountPresenter mAccountPresenter;
    private AccountRemoteDataSource mAccountRemoteDataSource;
    private AccountRepository mAccountRepository;
    private AccountAPIService mAccountAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account); //레이아웃 달아주시고.
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AccountFragment accountFragment =(AccountFragment)
                 getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (accountFragment == null) {
            accountFragment = AccountFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), accountFragment, R.id.contentFrame);
        }

        mAccountAPIService = new AccountAPIService(this);
        mAccountRemoteDataSource = new AccountRemoteDataSource(mAccountAPIService);
        mAccountRepository = new AccountRepository(mAccountRemoteDataSource);
        mAccountPresenter = new AccountPresenter(mAccountRepository,accountFragment,this);
    }

}
