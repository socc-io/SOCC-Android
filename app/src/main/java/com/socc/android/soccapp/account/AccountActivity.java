package com.socc.android.soccapp.account;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.WindowManager;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.network.AccountAPIService;
import com.socc.android.soccapp.utills.ActivityUtils;
import com.socc.android.soccapp.utills.SharePreferenceUtils;
import com.tsengvn.typekit.TypekitContextWrapper;


/**
 * Created by ksang on 2017-03-16.
 */
public class AccountActivity extends AppCompatActivity{

    private AccountPresenter mAccountPresenter;
    private AccountRemoteDataSource mAccountRemoteDataSource;
    private AccountRepository mAccountRepository;
    private AccountAPIService mAccountAPIService;
    private SharePreferenceUtils mSharePreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account); //레이아웃 달아주시고.
        mSharePreferenceUtils = new SharePreferenceUtils(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AccountFragment accountFragment =(AccountFragment)
                 getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (accountFragment == null) {
            accountFragment = AccountFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), accountFragment, R.id.contentFrame);
        }

        mAccountAPIService = new AccountAPIService();
        mAccountRemoteDataSource = new AccountRemoteDataSource(mAccountAPIService);
        mAccountRepository = new AccountRepository(mAccountRemoteDataSource);
        mAccountPresenter = new AccountPresenter(mAccountRepository,accountFragment,this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
