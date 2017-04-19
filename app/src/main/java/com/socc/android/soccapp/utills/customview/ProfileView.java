package com.socc.android.soccapp.utills.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by ksang on 2017-03-21.
 */
public class ProfileView extends FrameLayout implements Target{
    public ProfileView(Context context){
        super(context);
    }
    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
      //  BlurTransformation bf = new BlurTransformation(this.getContext(),25);
       // bf.
      // setBackground(new BitmapDrawable(this.getResources(),bitmap));
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
       // placeHolderDrawable.
    }
}
