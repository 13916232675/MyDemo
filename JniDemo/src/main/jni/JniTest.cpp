//
// Created by Q.Jay on 2016/5/8.
//
#include "JniTest.h"

/*
 * Class:     com_wang_walker_jnidemo_JniTest
 * Method:    getJniText
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_wang_walker_jnidemo_JniTest_getJniText
        (JNIEnv * env, jobject obj)
{
    return env->NewStringUTF("Hello Word From Jni <C++>");
}

/*
 * Class:     com_wang_walker_jnidemo_JniTest
 * Method:    logJniText
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT void JNICALL Java_com_wang_walker_jnidemo_JniTest_logJniText__
        (JNIEnv * env, jobject obj)
{
        //找到我们要调用的方法，注意包名+类名
        jclass clazz = env->FindClass("com/wang/walker/jnidemo/JniCall");
        //获取某个静态方法的ID
        //clazz 是我们上面找到的类的字节码文件
        //showToast 是clazz类中的方法名
        //"()V" 这个表示方法的签名；()是方法的参数列表；V表示方法的返回类型；V -> void
        jmethodID id = env->GetStaticMethodID(clazz, "logTest","()V");
        //最后调用这个方法，CallStaticVoidMethod(clazz,方法ID)
        env->CallStaticVoidMethod(clazz,id);
}

/*
 * Class:     com_wang_walker_jnidemo_JniTest
 * Method:    logJniText
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT void JNICALL Java_com_wang_walker_jnidemo_JniTest_logJniText__Ljava_lang_String_2
        (JNIEnv * env, jobject obj, jstring string)
{
        jclass clazz = env->FindClass("com/wang/walker/jnidemo/JniCall");
        jmethodID id = env->GetStaticMethodID(clazz, "logTest","(Ljava/lang/String;)V");
        env->CallStaticVoidMethod(clazz,id,string);
}
