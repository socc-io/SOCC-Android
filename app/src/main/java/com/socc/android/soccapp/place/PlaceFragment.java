package com.socc.android.soccapp.place;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.socc.android.soccapp.R;
import com.socc.android.soccapp.utills.ActivityUtils;
import com.socc.android.soccapp.utills.customview.CustomStarDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AppCreater01 on 2017-04-14.
 */
public class PlaceFragment extends Fragment implements PlaceContract.View , OnMapReadyCallback {
    @BindView(R.id.place_mapview) public MapView mMapView;
    @BindView(R.id.bottom_sheet) public LinearLayout llBottomSheet;
    @BindView(R.id.place_fab) public FloatingActionButton fab;
    @BindView(R.id.bs_ratingbar) public RatingBar btm_rating;
    @BindView(R.id.bs_image) public ImageView btm_image;
    @BindView(R.id.bs_title) public TextView btm_title;
    @BindView(R.id.bs_datetxt) public TextView btm_date;
    @BindView(R.id.bs_gradetxt) public TextView btm_grade;
    @BindView(R.id.floating_menu) public FloatingActionMenu mFabmenu;
    @BindView(R.id.fab_item01) public com.github.clans.fab.FloatingActionButton fabButton1;
    @BindView(R.id.fab_item02) public com.github.clans.fab.FloatingActionButton fabButton2;

    private GoogleMap mMap;
    private CustomStarDialog csd;
    private BottomSheetBehavior behavior;
    private Place mPlace;
    private PlaceContract.Presenter mPresenter;
    private PlaceContract.ReviewListener mReviewListener;

    public PlaceFragment() {
        // 프래그먼트 생성자.
    }

    public static PlaceFragment newInstance() {
        return new PlaceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_place, container, false);
        ButterKnife.bind(this, view);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
        fab.setVisibility(View.INVISIBLE);

        behavior = BottomSheetBehavior.from(llBottomSheet);
        csd = new CustomStarDialog(this.getContext());

        mReviewListener = new PlaceContract.ReviewListener() {
            @Override
            public void onReview(float stars,String placeid) {
                mPresenter.attemptGrade(placeid, stars);
                btm_rating.setRating(stars);

            }
        };
        csd.setReviewListener(mReviewListener);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng seoul = new LatLng( 37.56, 126.97 );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 14));

        mPresenter.getPlaces(); //플레이스 데이터 가져오기.

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(behavior.getState()==BottomSheetBehavior.STATE_EXPANDED){
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    fab.setVisibility(View.INVISIBLE);
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                //여기서 이제 통신으로 다시 플레이스 에 대한 구체적인 데이터를 가져와야 합니다.
                // 프레젠또로 가져오십쇼.
                //생각해보니 맨처음 플레이스 가져온 것으로 표기를 해도 될것 같습니다.
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                fab.setVisibility(View.VISIBLE);
                MarkerObject mo = (MarkerObject)marker.getTag();
                mPresenter.getPlace(mo.id);

                return false;
            }
        });
        //mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

    }
    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void drawMarker(ArrayList<Place> places) {
        int index =0;
        for(Place place : places){
            Marker marker = mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getPlaceName()));
            marker.setTag(new MarkerObject(place.getId(),index));
            index++;
        }
    }

    @Override
    public void drawBottomSheet(Place place) {
        //피카소  btm_image.
        Log.i("바텀쉿", place.getPlaceImageUrl());
        Picasso.with(this.getContext())
                .load(place.getPlaceImageUrl())
                //.placeholder(R.drawable.dwfox)
               // .resize(100, 100)
                .into(btm_image);
        this.mPlace = place;
        btm_title.setText(place.getPlaceName()); //타이틀
        btm_date.setText(place.getDate()); // 서브 (날짜)
        btm_rating.setRating(place.getGrade());
        btm_grade.setText("("+Float.toString(place.getGrade())+")");
    }

    @Override
    public void afterPlaceGrade(Place place) {
        Toast.makeText(this.getContext(), "소중한 평가 감사합니다.", Toast.LENGTH_LONG).show();
        drawBottomSheet(place);
    }

    @Override
    public void setPresenter(PlaceContract.Presenter presenter) {
        if(presenter !=null){
            Log.i("음 셋","프래젠또");
            mPresenter =presenter;
        }
    }
    @OnClick({R.id.place_fab, R.id.fab_item01, R.id.fab_item02})
    public void onSomeThingClick(View view) {
        switch (view.getId()) {
            case R.id.place_fab:
                csd.show(mPlace);
                break;
            case R.id.fab_item01:

                AddPlaceFragment addplaceFragment =(AddPlaceFragment)
                        this.getActivity().getSupportFragmentManager().findFragmentByTag("FRAGMENT_1");

                if (addplaceFragment == null) {
                    addplaceFragment = AddPlaceFragment.newInstance();
                    ActivityUtils.changeFragementToActivity(
                            this.getActivity().getSupportFragmentManager(), addplaceFragment, R.id.placeFrame);
                 //
                 //   Log.i("여기","걸리긴했는데..");
                }

                break;
            case R.id.fab_item02:
                break;
            default:
                break;

        }


    }

    private  class MarkerObject{
        String id;
        int index;
        private MarkerObject(String id,int index){
            this.id = id;
            this.index = index;
        }
    }

}
