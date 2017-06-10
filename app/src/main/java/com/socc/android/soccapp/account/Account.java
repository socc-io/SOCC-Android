package com.socc.android.soccapp.account;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;


/**
 * Created by ksang on 2017-03-16.
 */
public final class Account {
    //@NonNull
   // private final String mNum;
    @SerializedName("email")

    private final String mEmail;

    @SerializedName("password")
    private final String mPwd;

    @Nullable
    private final String mName;
    /**
     * Use this constructor to specify a completed Task if the Task already has an id (copy of
     * another Task).
     *
     * @param email       id of the account
     * @param pwd description of the task
     */
    public Account(@NonNull String email, @NonNull String pwd) {
        mEmail = email;
        mPwd = pwd;
        mName = null;
    }
    public Account(@NonNull String email, @NonNull String pwd, @NonNull String name){
        mEmail = email;
        mName = name;
        mPwd = pwd;
    }

    @NonNull
    public String getEmail() {
        return mEmail;
    }

    @Nullable
    public String getPwd() {
        return mPwd;
    }

    public boolean isEmpty() {
       return  mEmail.isEmpty() && mPwd.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(mEmail, account.mEmail) &&
                Objects.equals(mPwd, account.mPwd);
    }

    @Override
    public String toString() {
        return "Account with id is " + mEmail + "pwd is" + mPwd;
    }

    public class Result {
        @SerializedName("message") String message;
        @SerializedName("code") String code;

        public String getMessage() {return message;}
        public String getCode() {return code;}
    }


}
