package com.wang.walker.jnidemo;

import android.util.Log;

/**
 * Created by Holaverse on 2017/1/19.
 */

public class JniCall {
    private static final String TAG = "JniCall";

    public static void logTest() {
        Log.e(TAG, "logTest: ......");
    }

    public static void logTest(String s) {
        Log.e(TAG, "logTestString(String s): ......" + s);
    }
}
