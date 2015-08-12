#include <com_ytpdw_fun_login_LoginActivity.h>
#include <android/log.h>
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define LOG_TAG "log_tag"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)


JNIEXPORT jstring JNICALL Java_com_ytpdw_fun_login_LoginActivity_getStringName
  (JNIEnv * env, jobject obj){
    jstring str = (*env)->NewStringUTF(env, "HelloWorld from JNIoooooooooooowojiu zaideGGFGFFGGFGF!");
    return str;
 }


