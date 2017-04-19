package com.socc.android.soccapp.food;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Created by AppCreater01 on 2017-04-14.
 */
public class Food {
    @NonNull
    private final String mId;
    @NonNull
    private final String mName;
    @Nullable
    private final String mEating_date;
    @Nullable
    private final String mAttendcnt;
    @Nullable
    private final String mCreate_Date;
    ;

    /**
     * Use this constructor to specify a completed Task if the Task already has an id (copy of
     * another Task).
     *
     * @param id       id of the account
     * @param name       present of id on the study
     * @param eating_date       present of id on the study
     * @param attendcnt       present of id on the study
     */
    public Food(@NonNull String id,@NonNull String name, @NonNull String eating_date, String attendcnt, String create_date) {

        mId = id;
        this.mEating_date = eating_date;
        this.mName = name;
        this.mCreate_Date = create_date;
        this.mAttendcnt = attendcnt;
    }

    @NonNull
    public String getId() {
        return mId;
    }
    @Nullable
    public String getName() {
        return this.mName;
    }
    @Nullable
    public String getEating_date() {
        return this.mEating_date;
    }
    @Nullable
    public String getAttendcnt() { return this.mAttendcnt; }
    @Nullable
    public String getCreate_Date() { return this.mCreate_Date; }


    public boolean isEmpty() {
        return  mId.isEmpty() && mName.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Food food = (Food) o;
        return Objects.equals(mId, food.mId) &&
                Objects.equals(mName, food.mName) &&
                Objects.equals(mAttendcnt , food.mAttendcnt) &&
                Objects.equals(mEating_date , food.mEating_date) &&
                Objects.equals(mCreate_Date , food.mCreate_Date);
    }

    @Override
    public String toString() {
        return "THIS IS FoodHistory";
    }
}
