package com.socc.android.soccapp.utills.customview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.socc.android.soccapp.R;
import com.socc.android.soccapp.place.Place;
import com.socc.android.soccapp.place.PlaceContract;

/**
 * Created by AppCreater01 on 2017-04-16.
 */
public class CustomStarDialog implements DialogInterface.OnClickListener{
    private final static String DEFAULT_TITLE = "Rate this app";
    private final static String DEFAULT_TEXT = "How much do you love our app?";
    private final static String DEFAULT_POSITIVE = "Ok";
    private final static String DEFAULT_NEGATIVE = "Not Now";
    private final static String DEFAULT_NEVER = "Never";
    private final static String SP_NUM_OF_ACCESS = "numOfAccess";
    private static final String SP_DISABLED = "disabled";


    private TextView contentTextView;
    private RatingBar ratingBar;
    private Context context;
    private View dialogView;
    private AlertDialog alertDialog;
    private PlaceContract.ReviewListener reviewListener;
    private Place place;
    public CustomStarDialog(Context context){
        this.context = context;
    }

    private void build(Place place){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        dialogView = inflater.inflate(R.layout.dialog_star, null);
        String title ="";
        this.place = place;
        contentTextView = (TextView)dialogView.findViewById(R.id.text_content);
        contentTextView.setText(place.getPlaceName());

        ratingBar = (RatingBar) dialogView.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.d("체인지", "Rating changed : " + v);
                //ratingBar.setNumStars((Integer.v);
                /// reviewListener.onReview((int) ratingBar.getRating());

            }
        });

        alertDialog = builder.setTitle("쏘커님 만족도는 어느정도 이신가요?")
                .setView(dialogView)
                .setNegativeButton("Cancle", this)
                .setPositiveButton("Ok", this)
                .create();

    } //빌드 끝.

    public void show(Place place) {
            build(place);
            alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE){
            Log.i("파지티브","와뜸");
            if(reviewListener != null) {
                reviewListener.onReview(ratingBar.getRating(),place.getId());
            }
        }
        if(i == DialogInterface.BUTTON_NEGATIVE){

        }

        alertDialog.hide();
    }

    public void setTitle(String title) {
       // this.title = title;
        this.alertDialog.setTitle(title);
    }

    public void setRateText(String rateText){

    }

    public void setStarColor(int color){
      //  starColor = color;
    }

    public void setReviewListener(PlaceContract.ReviewListener listener){
        this.reviewListener = listener;
    }

}
