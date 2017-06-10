package com.socc.android.soccapp.signaccount;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.FileUtils;
import com.socc.android.soccapp.utills.FontUtils;
import com.squareup.picasso.Picasso;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ksang on 2017-04-20.
 */
public class SignUpProvisionFragment extends Fragment{

    @BindView(R.id.provision_personal)
    TextView personal_txt;
    @BindView(R.id.provision_use)
    TextView use_txt;
    @BindView(R.id.provision_all_chk)
    CheckBox allcheck;
    @BindView(R.id.provision_use_chk)
    CheckBox use_check;
    @BindView(R.id.provision_personal_chk)
    CheckBox personal_check;

    private DataManager.Datapasenger dataManager;

    public SignUpProvisionFragment() {
        // 프래그먼트 생성자.
    }

    public static SignUpProvisionFragment newInstance() {
        return new SignUpProvisionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_signup_provision, container, false);
        ButterKnife.bind(this, root);
       // FontUtils.setGlobalFont(this.getContext(),root,"NotoSansCJKkr Regular.otf");
        personal_txt.setMovementMethod(ScrollingMovementMethod.getInstance());
        use_txt.setMovementMethod(ScrollingMovementMethod.getInstance());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataManager.Datapasenger) {
            dataManager = (DataManager.Datapasenger) context;
        } else {
            throw new IllegalStateException("Activity must implement DataManager interface!");
        }
    }

    @OnCheckedChanged(R.id.provision_all_chk)
    public void checkboxToggled (boolean isChecked) {
        if(isChecked){
            use_check.setChecked(true);
            personal_check.setChecked(true);
            dataManager.confirmChk(true);
        }else{
            if(use_check.isChecked() && personal_check.isChecked()){
                use_check.setChecked(false);
                personal_check.setChecked(false);
                dataManager.confirmChk(false);
            }
        }
    }

    @OnCheckedChanged(R.id.provision_personal_chk)
    public void checkbox2Toggled (boolean isChecked) {
        if(isChecked){
            if(use_check.isChecked()){
                allcheck.setChecked(true);
            }
        }else{
            allcheck.setChecked(false);
        }
    }

    @OnCheckedChanged(R.id.provision_use_chk)
    public void checkbox3Toggled (boolean isChecked) {
        if(isChecked){
            if(personal_check.isChecked()){
                allcheck.setChecked(true);
            }
        }else{
            allcheck.setChecked(false);
        }
    }

}
