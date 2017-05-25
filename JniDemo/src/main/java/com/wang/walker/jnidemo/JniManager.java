package com.wang.walker.jnidemo;

/**
 * Created by Holaverse on 2017/1/20.
 */

public class JniManager {
    static {
        System.loadLibrary("JniTest");
    }

    private static final JniTest jniTest = new JniTest();

    public static String getJniText() {
        return jniTest.getJniText();
    }


    public static void logJniText() {
        jniTest.logJniText();
    }

    public static void logJniText(String string) {
        jniTest.logJniText(string);
    }
}
