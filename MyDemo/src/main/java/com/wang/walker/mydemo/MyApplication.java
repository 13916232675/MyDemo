package com.wang.walker.mydemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Holaverse on 2017/1/25.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
