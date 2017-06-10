package com.socc.android.soccapp.signaccount;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.Account;
import com.socc.android.soccapp.utills.DLogUtils;
import com.socc.android.soccapp.utills.FileUtils;
import com.socc.android.soccapp.utills.customview.RoundedTransformation;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ksang on 2017-04-20.
 */
public class SignUpProfileFragment extends Fragment {
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_ALBUM = 2;
    private static final String TYPE_IMAGE = "image/*";
    private static final int PROFILE_IMAGE_ASPECT_X = 1;
    private static final int PROFILE_IMAGE_ASPECT_Y = 1;
    private static final int PROFILE_IMAGE_OUTPUT_X = 300;
    private static final int PROFILE_IMAGE_OUTPUT_Y = 300;
    private boolean isCamera = false;
    PermissionListener permissionlistener;
    private Uri mTempImageUri;
    private Uri mImageCaptureUri ;
    private DataManager.Datapasenger dataManager;

    @BindView(R.id.signup_profile_img)
    ImageView profile_img;

    public SignUpProfileFragment() {
        // 프래그먼트 생성자.
    }


    public static SignUpProfileFragment newInstance() {
        return new SignUpProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_signup_profile, container, false);
        ButterKnife.bind(this, root);

        permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent,PICK_FROM_ALBUM);
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                //  Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DLogUtils.i("리쥼");
    }
    @Override
    public void onDestroy(){
        DLogUtils.i("디스트로이");
        super.onDestroy();
    }

    @OnClick({R.id.signup_profile_img})
    public void onSomeThingClick(View view) {
        switch (view.getId()) {
            case R.id.signup_profile_img:
                new TedPermission(this.getContext())
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requstCode,int resultCode, Intent data){
        super.onActivityResult(requstCode,resultCode,data);
        if(resultCode != this.getActivity().RESULT_OK){
            return;
        }
        switch (requstCode){
            case PICK_FROM_ALBUM:
                mTempImageUri = data.getData();
                File original_file = FileUtils.getImageFile(mTempImageUri,this.getActivity().getContentResolver());
                if(original_file !=null){
                    DLogUtils.i(original_file.toString());
                }else{
                    DLogUtils.i("널인데?");
                }
                mTempImageUri = FileUtils.createSaveCropFile();
                File copy_file = new File(mTempImageUri.getPath());
                boolean flag = FileUtils.copyFile(original_file , copy_file);

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mTempImageUri, TYPE_IMAGE);
                intent.putExtra("output", mTempImageUri);
                intent.putExtra("outputX", PROFILE_IMAGE_OUTPUT_X); // crop한 이미지의 x축 크기
                intent.putExtra("outputY", PROFILE_IMAGE_OUTPUT_Y); // crop한 이미지의 y축 크기
                intent.putExtra("aspectX", PROFILE_IMAGE_ASPECT_X); // crop 박스의 x축 비율
                intent.putExtra("aspectY", PROFILE_IMAGE_ASPECT_Y); // crop 박스의 y축 비율
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_ALBUM);
                break;
            case CROP_FROM_ALBUM:
                if(resultCode == this.getActivity().RESULT_OK){

                 //   profile_img.setLayoutParams();

                    Picasso.with(this.getContext()).load(mTempImageUri).resize(500,500)
                            .transform(new RoundedTransformation())
                            .into(profile_img);
                    isCamera = true;
                    dataManager.saveProfileUrl(mTempImageUri.getPath());
                }
                File file = new File(mTempImageUri.getPath());
                //mPresenter.uploadImage(mTempImageUri.getPath());
                if (file.exists()) {
                    // file.delete();
                }
                break;
            default:
                break;
        }
    }
}
