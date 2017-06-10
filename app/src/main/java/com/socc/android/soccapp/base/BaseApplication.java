package com.socc.android.soccapp.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.socc.android.soccapp.utills.DLogUtils;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by ksang on 2017-04-22.
 */
public class BaseApplication extends Application {
    public static boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "NotoSansMonoCJKkr Regular.otf"))
                .addBold(Typekit.createFromAsset(this, "Spoqa Han Sans Bold.ttf"))
                .addCustom1(Typekit.createFromAsset(this, "Spoqa Han Sans Regular.ttf"));

        this.DEBUG = isDebuggable(this);
    }

    private boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));

        } catch (PackageManager.NameNotFoundException e) {
			/* debuggable variable will remain false */
        }

        return debuggable;
    }

}
