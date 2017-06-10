package com.socc.android.soccapp.signaccount;

import com.socc.android.soccapp.account.Account;

/**
 * Created by ksang on 2017-05-05.
 */
public interface DataManager {
     interface Datapasenger{
        void saveProfileUrl(String url);
        void setFinishListener(finished_listener listener);
        void confirmChk(boolean flag);
        String getProfileUrl();
    }

    interface finished_listener{
        Account onFinish();
    }
}

