package com.socc.android.soccapp.study;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Created by ksang on 2017-04-01.
 */
public class Study {
    @NonNull
    private final String mOwner_id;
    @Nullable
    private final String mId;
    @Nullable
    private final String mDescription;
    @Nullable
    private final String mName;
    @Nullable
    private final String mCreate_Date;


    /**
     * Use this constructor to specify a completed Task if the Task already has an id (copy of
     * another Task).
     *
     * @param id       id of the account
     * @param ownerid       present of id on the study
     */
    public Study(@NonNull String ownerid,@NonNull String id, @NonNull String descript, String name, String create_date) {
        this.mOwner_id = ownerid;
        mId = id;
        this.mDescription = descript;
        this.mName = name;
        this.mCreate_Date = create_date;
    }

    @NonNull
    public String getId() {
        return mId;
    }
    @Nullable
    public String getOwnerId() {
        return this.mOwner_id;
    }
    @Nullable
    public String getName() {
        return this.mName;
    }
    @Nullable
    public String getmDescription() { return this.mDescription; }
    @Nullable
    public String getmCreate_Date() { return this.mCreate_Date; }


    public boolean isEmpty() {
        return  mId.isEmpty() && mOwner_id.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        return Objects.equals(mOwner_id,study.mOwner_id) &&
                Objects.equals(mId, study.mId) &&
                Objects.equals(mDescription , study.mDescription) &&
                Objects.equals(mName , study.mName) &&
                Objects.equals(mCreate_Date , study.mCreate_Date);
    }

    @Override
    public String toString() {
        return "Study with id";
    }
}
