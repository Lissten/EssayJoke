package com.test.project.modulecommon.rxbus.bean;

/**
 * Author    zhouchuan
 * Describe：Activity生命周期
 * Data:      2019/5/17 12:05
 * Modify by
 * Modification date：
 * Modify content：
 */
public class ActivityLifecycleBean {

    private int lifecycleCode;

    public ActivityLifecycleBean() {

    }

    public ActivityLifecycleBean(int lifecycleCode) {
        this.lifecycleCode = lifecycleCode;
    }

    public int getLifecycleCode() {
        return lifecycleCode;
    }

    public void setLifecycleCode(int lifecycleCode) {
        this.lifecycleCode = lifecycleCode;
    }

    public interface LifecycleCode {
        int ON_CREATE = 0;
        int ON_START = 1;
        int ON_RESUME = 2;
        int ON_RESTART = 3;
        int ON_PAUSE = 4;
        int ON_STOP = 5;
        int ON_DESTORY = 6;
        int ON_FOREGROUND = 7;
        int ON_BACKGROUND = 8;
    }
}
