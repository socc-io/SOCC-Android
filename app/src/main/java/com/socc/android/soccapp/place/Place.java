package com.socc.android.soccapp.place;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by AppCreater01 on 2017-04-14.
 */
public class Place {
        String mName; //먹었던 제목 / 이름
        String mId; //플레이스 아이디
        String mPlaceName; //먹었던 장소 이름
        LatLng mLatLng; //플레이스 좌표 데이터
        Double mLaititude;
        Double mLongitude;
        char mPlaceType; //플레이스 타입.
        String mPrice; // 플레이스 가격
        String mPlaceImageUrl; //먹었던 장소 이미지 유알엘.
        String mDate; // 먹었던 날짜.
        float mGrade; //평점...

    public Place(String id, String placeName,String name,LatLng latlng, char placetype , String price,String url,String date,float grade){
        this.mId = id;
        this.mName = name;
        this.mLatLng = latlng;
        this.mPlaceType = placetype;
        this.mPrice  = price;
        this.mPlaceName = placeName;
        this.mPlaceImageUrl = url;
        this.mDate = date;
        this.mGrade = grade;
    }
    public String getId(){
        return this.mId;
    }
    public String getName(){
        return this.mName;
    }
    public LatLng getLatLng(){
        return this.mLatLng;
    }
    public String getPlaceName(){
        return this.mPlaceName;
    }
    public String getPrice(){
        return this.mPrice;
    }
    public String getPlaceImageUrl(){
        return this.mPlaceImageUrl;
    }
    public String getDate(){
        return this.mDate;
    }
    public float getGrade(){return this.mGrade;
    }
    public void setId(String id){
        this.mId = id;
    }
    public void setName(String name){
        this.mName = name;
    }
    public void setLatLng(LatLng latlng){this.mLatLng = latlng;}
    public void setPlaceName(String name){
        this.mPlaceName = name;
    }
    public void setPlaceImageUrl(String url){
        this.mPlaceImageUrl = url;
    }
    public void setPrice(String price){this.mPrice = price;}
    public void setDate(String date){
        this.mDate = date;
    }
    public void setGrage(float grade){this.mGrade = grade;}

}
