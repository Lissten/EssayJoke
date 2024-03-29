package com.test.project.modulecommon.utils.log;

import android.support.annotation.NonNull;

import com.test.project.modulecommon.utils.log.inner.ILogImpl;
import com.test.project.modulecommon.utils.log.inner.LogInner;

/**
 * 对{@link ILog}的简单封装, 使其可以直接使用
 * Created by Simon on 2016/9/5.
 */
public class ILogger {
    private ILogger() {
    }

    private static ILogImpl mLog = new ILogImpl();

    /**
     * 基本的初始化, 必须设置tag
     *
     * @param baseTag 默认的tag, 当调用没有tag的方法打印时, 将使用此tag
     */
    public static Settings init(@NonNull String baseTag) {
        LogInner.printVersionInfo();
        mLog.getSettings().tag(baseTag);
        return mLog.getSettings();
    }

    private static Settings tempSetting;

    /**
     * 修改下一条打印的log, 是否显示线程信息
     *
     * @param showThread true 显示线程信息; false 不显示
     */
    public static ILogger thread(boolean showThread) {
        mLog.thread(showThread);
        return new ILogger();
    }

    /**
     * 修改下一条打印的log, 显示的堆栈层数
     */
    public static ILogger method(int methodCount) {
        mLog.method(methodCount);
        return new ILogger();
    }

    /**
     * 修改下一条打印的log, 显示的tag
     */
    public static ILogger tag(String tag) {
        mLog.tag(tag);
        return new ILogger();
    }


    //start: 提供和系统自带 android.util.Log 一模一样的接口
    public static void v(String tag, String msg) {
        v(tag, msg, null);
    }

    public static void v(String tag, String msg, Throwable tr) {
        mLog.log(ILog.VERBOSE, tempSetting, tag, tr, msg);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable tr) {
        mLog.log(ILog.DEBUG, tempSetting, tag, tr, msg);
    }

    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable tr) {
        mLog.log(ILog.INFO, tempSetting, tag, tr, msg);
    }

    public static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, Throwable tr) {
        w(tag, null, tr);
    }

    public static void w(String tag, String msg, Throwable tr) {
        mLog.log(ILog.WARN, tempSetting, tag, tr, msg);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        mLog.log(ILog.ERROR, tempSetting, tag, tr, msg);
    }

    public static void wtf(String tag, String msg) {
        wtf(tag, msg, null);
    }

    public static void wtf(String tag, Throwable tr) {
        wtf(tag, null, tr);
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        mLog.log(ILog.ASSERT, tempSetting, tag, tr, msg);
    }
    //end: 提供和系统自带 android.util.Log 一模一样的接口

    public static void v(String msg) {
        v(null, msg);
    }

    public static void d(String msg) {
        d(null, msg);
    }

    public static void i(String msg) {
        i(null, msg);
    }

    public static void w(String msg) {
        w(null, msg, null);
    }

    public static void w(Throwable e) {
        w(e, null);
    }

    public static void w(Throwable e, String msg) {
        w(null, msg, e);
    }

    public static void e(String msg) {
        e(null, msg, null);
    }

    public static void e(Throwable e) {
        e(e, null);
    }

    public static void e(Throwable tr, String msg) {
        e(null, msg, tr);
    }

    public static void wtf(String msg) {
        wtf(null, msg, null);
    }

    public static void wtf(Throwable e, String msg) {
        wtf(null, msg, e);
    }

    public static void json(String json) {
        mLog.json(json);
    }

}
