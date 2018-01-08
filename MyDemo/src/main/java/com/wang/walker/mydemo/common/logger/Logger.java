package com.wang.walker.mydemo.common.logger;

import android.util.Log;

/**
 * Created by Holaverse on 2017/9/1.
 */

public class Logger {
    private static final String TAG = "MyDemo";

    public static void log(String message) {
        Log.i(TAG, message);
    }
}
