package com.socc.android.soccapp.utills;

import android.util.Log;

import com.socc.android.soccapp.base.BaseApplication;

/**
 * Created by ksang on 2017-04-22.
 */
public class DLogUtils {
    static final String TAG = "Ksang91";
    static final boolean IsLog = true;

    /** Log Level Error **/
    public static final void e(String message) {
        if (IsLog)
            Log.e(TAG, buildLogMsg(message));
    } /** Log Level Warning **/
    public static final void w(String message) {
        if (IsLog)
            Log.w(TAG, buildLogMsg(message));
    } /** Log Level Information **/
    public static final void i(String message) {
        if (IsLog)
            Log.i(TAG, buildLogMsg(message));
    }
    /** Log Level Debug **/
    public static final void d(String message) {
        if (IsLog)
            Log.d(TAG, buildLogMsg(message));
    }
    /** Log Level Verbose **/
    public static final void v(String message) {
        if (IsLog)
            Log.v(TAG, buildLogMsg(message));
    }
    public static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);
        return sb.toString();
    }

}
