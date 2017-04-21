package com.socc.android.soccapp.signaccount;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.account.AccountContract;
import com.socc.android.soccapp.main.MainActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by ksang on 2017-04-20.
 */
public class SignUpFragment extends Fragment implements SignUpContract.View {

    private SignUpContract.Presenter mPresenter;
    private static final int REQUEST_CODE_PROFILE_IMAGE_PICK = 545;

    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_ALBUM = 2;

    private static final String TYPE_IMAGE = "image/*";
    private static final int PROFILE_IMAGE_ASPECT_X = 3;
    private static final int PROFILE_IMAGE_ASPECT_Y = 1;
    private static final int PROFILE_IMAGE_OUTPUT_X = 600;
    private static final int PROFILE_IMAGE_OUTPUT_Y = 200;
    private static final String TEMP_FILE_NAME = "profileImageTemp.jpg";
    private Uri mTempImageUri;


    @BindView(R.id.layout_user_name)
    TextInputLayout layoutUserName ;
    @BindView(R.id.layout_user_password)
    TextInputLayout layoutPassword ;

    @BindView(R.id.profile_img)
    ImageView mProfileImg;


    public SignUpFragment() {
        // 프래그먼트 생성자.
    }

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_signup, container, false);
        ButterKnife.bind(this, root);

        Picasso.with(this.getContext()).load(R.drawable.default_profile_pic)
                .transform(new CropCircleTransformation())
                .into(mProfileImg);

        setHasOptionsMenu(false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void successSignUp() {

    }

    @Override
    public void failedSignUp(String msg) {

    }

    @Override
    public void attemptSignUp() {

    }

    @Override
    public void setPresenter(SignUpContract.Presenter presenter) {
        if(presenter !=null){
            mPresenter =presenter;
        }
    }

    @OnClick(R.id.profile_img)
    public void onSomeThingClick(View view) {
        switch (view.getId()) {
            case R.id.profile_img:
                int permissionReadStorage = ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                int permissionWriteStorage = ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if(permissionReadStorage == PackageManager.PERMISSION_DENIED || permissionWriteStorage == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this.getContext(),"권한 획득 실패",Toast.LENGTH_SHORT);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    startActivityForResult(intent,PICK_FROM_ALBUM);
                }

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
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mTempImageUri, "image/*");
                intent.putExtra("outputX", 250); // crop한 이미지의 x축 크기
                intent.putExtra("outputY", 250); // crop한 이미지의 y축 크기
                intent.putExtra("aspectX", 1); // crop 박스의 x축 비율
                intent.putExtra("aspectY", 1); // crop 박스의 y축 비율
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_ALBUM);
                break;
            case CROP_FROM_ALBUM:
                 Bundle extras = data.getExtras();
                 String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp/" + System.currentTimeMillis() + ".jpg";
                 Log.i("파일",filePath);

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data"); // crop된 bitmap
                    String path = MediaStore.Images.Media.insertImage(this.getActivity().getContentResolver(),photo, "crop_image", null);
                    //storeCropImage(photo, filePath);
                    Log.i("피카소","시작");
                    Picasso.with(this.getContext()).load(path)
                            .transform(new CropCircleTransformation())
                            .into(mProfileImg);
                    Log.i("피카소","종료");
                    //this.getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()))); // 갤러리를 갱신하기 위해..
                }

                File file = new File(mTempImageUri.getPath());
                if (file.exists()) {
                    file.delete();
                }
                break;
            default:
                break;
        }
    }
    private void storeCropImage(Bitmap bitmap, String filePath) {

        File copyFile = new File(filePath);
        Log.i("피카소",filePath);
        BufferedOutputStream out = null;
        try {
            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("저장","종료");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.i("먼데",Integer.toString(requestCode));
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this.getActivity(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // add other cases for more permissions
        }
    }

}
