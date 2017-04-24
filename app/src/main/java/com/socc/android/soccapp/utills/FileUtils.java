package com.socc.android.soccapp.utills;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ksang on 2017-04-23.
 */
public class FileUtils {

    public static Uri createSaveCropFile(){
        File file = new File("/sdcard/socc/");
        if (!file.isDirectory()) {
            file.mkdir();
        }
        Uri uri;
        String url = "socc" + String.valueOf(System.currentTimeMillis()) + ".jpg";
       // String strPhotoName = url;
        uri = Uri.fromFile(new File("/sdcard/socc/", url));
        DLogUtils.i("url은"+url);
        DLogUtils.i(uri.toString());


        return uri;
    }

    /**
     * @author pppdw
     * @param  uri 사진경로를 얻는다,Uri가 널이되는 경우엔, 가장최근(라스트인덱스) 사진을 가지고온다.
     * @return File (해당사진)
     */
    public static File getImageFile(Uri uri, ContentResolver cr) {
        String[] projection = { MediaStore.Images.Media.DATA };
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        Cursor mCursor = cr.query(uri, projection, null, null,
                MediaStore.Images.Media.DATE_MODIFIED + " desc");
        if(mCursor == null || mCursor.getCount() < 1) {
            return null; // no cursor or no record
        }
        int column_index = mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        mCursor.moveToFirst();

        String path = mCursor.getString(column_index);
        DLogUtils.i(path);
        if (mCursor !=null ) {
            mCursor.close();
            mCursor = null;
        }
        return new File(path);
    }

    /**
     * @author pppdw
     * @description 크롭을 위해 사진을 복사한다.
     * @return
     */
    public static boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        try {
            InputStream in = new FileInputStream(srcFile);
            try {
                result = copyToFile(in, destFile);
            } finally  {
                in.close();
            }
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    /**
     * @author : pppdw
     * @description : DestFile을 소스스트림에 복사한다 (데이터밸류)
     */
    public static boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            OutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
