package com.wang.walker.jnidemo;

/**
 * Created by Holaverse on 2017/1/18.
 */

public class JniTest {

    public native String getJniText();

    public native void logJniText();

    public native void logJniText(String string);

}
