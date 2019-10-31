package com.test.project.modulecommon.utils.log;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.test.project.modulecommon.utils.log.inner.ILogImpl;

public class LogUtils {
    public static final int SYSTEM = 0;
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    private static boolean sDebug = false;
    private static int sLogLevel = 0;
    private static ILog sLog;
    public static LogUtils.Tag TAG = new LogUtils.Tag("Def");

    private LogUtils() {
    }

    @SuppressLint("WrongConstant")
    public static void initLog(boolean debug, String tagPrefix) {
        initLog(debug, tagPrefix, 0);
    }

    public static void initLog(boolean debug, String tagPrefix, @ILog.Level int logLevel) {
        LogUtils.Builder builder = (new LogUtils.Builder()).setDebug(debug).tagPrefix(tagPrefix).showLog(true).logLevel(logLevel).showThreadInfo(debug).methodCount(debug ? 1 : 0).methodOffset(1).saveLog(false, (String)null);
        initLog(builder);
    }

    public static void initLog(@NonNull LogUtils.Builder builder) {
        sLog = builder.build();
        sDebug = builder.settings.isDebug();
        sLogLevel = builder.settings.getLogLevel();
        LogUtils.Tag.init(builder.settings.getTagPrefix());
        TAG = new LogUtils.Tag("Def");
    }

    private static synchronized ILog getSLog() {
        if (sLog == null) {
            initLog(false, LogUtils.Tag.sLOG_TAG_PREFIX);
        }

        return sLog;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static int getLogLevel() {
        return sLogLevel;
    }

    public static void v(LogUtils.Tag tag, String msg) {
        if (isLoggable(tag.toString(), 2)) {
            getSLog().tag(tag.toString()).v(msg);
        }

    }

    public static void d(LogUtils.Tag tag, String msg) {
        if (isLoggable(tag.toString(), 3)) {
            getSLog().tag(tag.toString()).d(msg);
        }

    }

    public static void i(LogUtils.Tag tag, String msg) {
        if (isLoggable(tag.toString(), 4)) {
            getSLog().tag(tag.toString()).i(msg);
        }

    }

    public static void w(LogUtils.Tag tag, String msg) {
        if (isLoggable(tag.toString(), 5)) {
            getSLog().tag(tag.toString()).w(msg);
        }

    }

    public static void w(LogUtils.Tag tag, String msg, Throwable tr) {
        if (isLoggable(tag.toString(), 5)) {
            getSLog().tag(tag.toString()).w(tr, msg);
        }

    }

    public static void e(LogUtils.Tag tag, String msg) {
        if (isLoggable(tag.toString(), 6)) {
            getSLog().tag(tag.toString()).e(msg);
        }

    }

    public static void e(LogUtils.Tag tag, String msg, Throwable tr) {
        if (isLoggable(tag.toString(), 6)) {
            getSLog().tag(tag.toString()).e(tr, msg);
        }

    }

    public static void wtf(LogUtils.Tag tag, String msg) {
        if (isLoggable(tag.toString(), 7)) {
            getSLog().tag(tag.toString()).wtf(msg);
        }

    }

    public static void wtf(LogUtils.Tag tag, String msg, Throwable tr) {
        if (isLoggable(tag.toString(), 7)) {
            getSLog().tag(tag.toString()).wtf(tr, msg);
        }

    }

    public static void v(String msg) {
        if (isLoggable(TAG.toString(), 2)) {
            getSLog().tag(TAG.toString()).v(msg);
        }

    }

    public static void d(String msg) {
        if (isLoggable(TAG.toString(), 3)) {
            getSLog().tag(TAG.toString()).d(msg);
        }

    }

    public static void i(String msg) {
        if (isLoggable(TAG.toString(), 4)) {
            getSLog().tag(TAG.toString()).i(msg);
        }

    }

    public static void w(String msg) {
        if (isLoggable(TAG.toString(), 5)) {
            getSLog().tag(TAG.toString()).w(msg);
        }

    }

    public static void w(String msg, Throwable tr) {
        if (isLoggable(TAG.toString(), 5)) {
            getSLog().tag(TAG.toString()).w(tr, msg);
        }

    }

    public static void e(String msg) {
        if (isLoggable(TAG.toString(), 6)) {
            getSLog().tag(TAG.toString()).e(msg);
        }

    }

    public static void e(String msg, Throwable tr) {
        if (isLoggable(TAG.toString(), 6)) {
            getSLog().tag(TAG.toString()).e(tr, msg);
        }

    }

    public static void wtf(String msg) {
        if (isLoggable(TAG.toString(), 7)) {
            getSLog().tag(TAG.toString()).wtf(msg);
        }

    }

    public static void wtf(String msg, Throwable tr) {
        if (isLoggable(TAG.toString(), 7)) {
            getSLog().tag(TAG.toString()).wtf(tr, msg);
        }

    }

    public static void v(String tag, String msg) {
        tag = initTag(tag);
        if (isLoggable(tag, 2)) {
            getSLog().tag(tag).v(msg);
        }

    }

    public static void d(String tag, String msg) {
        tag = initTag(tag);
        if (isLoggable(tag, 3)) {
            getSLog().tag(tag).d(msg);
        }

    }

    public static void i(String tag, String msg) {
        tag = initTag(tag);
        if (isLoggable(tag, 4)) {
            getSLog().tag(tag).i(msg);
        }

    }

    public static void w(String tag, String msg) {
        tag = initTag(tag);
        if (isLoggable(tag, 5)) {
            getSLog().tag(tag).w(msg);
        }

    }

    public static void w(String tag, String msg, Throwable tr) {
        tag = initTag(tag);
        if (isLoggable(tag, 5)) {
            getSLog().tag(tag).w(tr, msg);
        }

    }

    public static void e(String tag, String msg) {
        tag = initTag(tag);
        if (isLoggable(tag, 6)) {
            getSLog().tag(tag).e(msg);
        }

    }

    public static void e(String tag, String msg, Throwable tr) {
        tag = initTag(tag);
        if (isLoggable(tag, 6)) {
            getSLog().tag(tag).e(tr, msg);
        }

    }

    public static void wtf(String tag, String msg) {
        tag = initTag(tag);
        if (isLoggable(tag, 7)) {
            getSLog().tag(tag).wtf(msg);
        }

    }

    public static void wtf(String tag, String msg, Throwable tr) {
        tag = initTag(tag);
        if (isLoggable(tag, 7)) {
            getSLog().tag(tag).wtf(tr, msg);
        }

    }

    private static void warn(String tag, String msg) {
        getSLog().tag(tag).w(msg);
    }

    private static void error(LogUtils.Tag tag, String msg) {
        getSLog().tag(tag.toString()).e(msg);
    }

    private static boolean isLoggable(String tag, int level) {
        try {
            if (isDebug()) {
                return true;
            } else if (getLogLevel() != 0) {
                return getLogLevel() <= level;
            } else {
                return Log.isLoggable(LogUtils.Tag.sLOG_TAG_PREFIX, level) || Log.isLoggable(tag, level);
            }
        } catch (IllegalArgumentException var3) {
            error(TAG, "Tag too long:" + tag);
            return false;
        }
    }

    private static String initTag(String tag) {
        if (TextUtils.isEmpty(tag)) {
            tag = "";
        }

        int lenDiff = tag.length() - LogUtils.Tag.sMaxTagLen;
        if (lenDiff > 0) {
            warn(TAG == null ? LogUtils.Tag.sLOG_TAG_PREFIX : TAG.toString(), "Tag " + tag + " is " + lenDiff + " chars longer than limit.");
        }

        tag = LogUtils.Tag.sLOG_TAG_PREFIX + (lenDiff > 0 ? tag.substring(0, LogUtils.Tag.sMaxTagLen) : tag);
        return tag;
    }

    public static final class Tag {
        private static String sLOG_TAG_PREFIX = "Stu_";
        private static int sMaxTagLen;
        final String mValue;

        private static void init(String logTagPrefix) {
            sLOG_TAG_PREFIX = logTagPrefix == null ? "" : logTagPrefix;
            sMaxTagLen = 23 - sLOG_TAG_PREFIX.length();
        }

        public Tag(Class tagClass) {
            this(tagClass == null ? null : tagClass.getSimpleName());
        }

        public Tag(String tag) {
            this.mValue = LogUtils.initTag(tag);
        }

        public String toString() {
            return this.mValue;
        }

        static {
            sMaxTagLen = 23 - sLOG_TAG_PREFIX.length();
        }
    }

    public static class Builder {
        final SettingsExt settings = new SettingsExt();

        public Builder() {
        }

        public LogUtils.Builder setDebug(boolean debug) {
            this.settings.setDebug(debug);
            return this;
        }

        public LogUtils.Builder tagPrefix(String tagPrefix) {
            this.settings.setTagPrefix(tagPrefix);
            return this;
        }

        public LogUtils.Builder logLevel(@ILog.Level int logLevel) {
            this.settings.setLogLevel(logLevel);
            return this;
        }

        public LogUtils.Builder showLog(boolean shouldLog) {
            this.settings.showLog(shouldLog);
            return this;
        }

        public LogUtils.Builder methodCount(int methodCount) {
            this.settings.methodCount(methodCount);
            return this;
        }

        public LogUtils.Builder methodOffset(int methodOffset) {
            this.settings.methodOffset(methodOffset);
            return this;
        }

        public LogUtils.Builder showThreadInfo(boolean shouldShowThreadInfo) {
            this.settings.showThreadInfo(shouldShowThreadInfo);
            return this;
        }

        public LogUtils.Builder saveLog(boolean shouldSave, String logSaveLocation) {
            this.settings.saveLog(shouldSave, logSaveLocation);
            return this;
        }

        public ILog build() {
            return new ILogImpl(this.settings);
        }
    }
}

