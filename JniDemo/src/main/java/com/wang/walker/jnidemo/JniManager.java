package com.wang.walker.jnidemo;

/**
 * Created by Holaverse on 2017/1/20.
 */

public class JniManager {
    static {
        System.loadLibrary("JniTest");
    }

    // 通过jniTest中的native方法，调用so文件中对应的方法
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
