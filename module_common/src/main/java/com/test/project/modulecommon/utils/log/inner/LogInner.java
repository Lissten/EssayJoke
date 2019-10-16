package com.test.project.modulecommon.utils.log.inner;

import android.util.Log;

import com.test.project.modulecommon.utils.log.SDKVersion;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/16 16:24
 * Modify by
 * Modification date：
 * Modify content：
 */
public class LogInner {

    public static void w(String tag, Throwable e, String msg){
        Log.w(tag, msg, e);
    }

    public static void e(String tag, Throwable e, String msg){
        Log.e(tag, msg, e);
    }

    public static void e(String tag,  String msg){
        Log.e(tag, msg);
    }

    /***
     * 打印版本信息  方便追踪app使用的版本
     */
    public static void printVersionInfo(){
        Log.i("日志库版本信息"," " + SDKVersion.getLibraryName() + " init, version: " +
            SDKVersion.getVersionName() + "  code: " + SDKVersion.getSDKInt());
    }
}
