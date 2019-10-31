package com.test.project.modulecommon.utils.log;

public class SettingsExt extends Settings {

    private boolean debug = false;
    private String tagPrefix;
    private int logLevel = LogUtils.SYSTEM;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getTagPrefix() {
        return tagPrefix;
    }

    public void setTagPrefix(String tagPrefix) {
        this.tagPrefix = tagPrefix;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }
}

