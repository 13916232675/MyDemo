package com.wang.walker.jnidemo;

/**
 * Created by Holaverse on 2017/1/18.
 */

// 根据此文件生成.h文件，java中就可以通过native方法调用.h文件中的方法
public class JniTest {

    public native String getJniText();

    public native void logJniText();

    public native void logJniText(String string);

}
