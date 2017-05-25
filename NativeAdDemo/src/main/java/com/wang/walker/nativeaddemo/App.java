package com.wang.walker.nativeaddemo;

import android.app.Application;

/**
 * Created by Holaverse on 2017/1/13.
 */

public class App extends Application {
    private static App sApp;

    @Override
    public void onCreate() {
        sApp = this;
    }

    public static App getApp() {
        return sApp;
    }
}
