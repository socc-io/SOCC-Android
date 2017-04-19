package com.socc.android.soccapp.utills;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AppCreater01 on 2017-04-09.
 */
public class JsonResultVoUtils {
    @SerializedName("success") int success;
    @SerializedName("msg") String msg;
    @SerializedName("user") user mUser;

    public class user {
        @SerializedName("active") String active;
        @SerializedName("apikey") String apikey;
        @SerializedName("created_date") String created_date;
        @SerializedName("email") String email;
        @SerializedName("id") String id;
        @SerializedName("last_date") String last_date;
        @SerializedName("role") String role;

        public String getActive() {return active;}
        public String getApikey() {return apikey;}
        public String getCreated_date() {return created_date;}
        public String getEmail() {return email;}
        public String getId() {return id;}
        public String getLast_date() {return last_date;}
        public String getRole() {return role;}

    }

    public user getUser() {return mUser;}
    public int getSuccess() {return success;}
    public String getMsg() {return msg;}
}
