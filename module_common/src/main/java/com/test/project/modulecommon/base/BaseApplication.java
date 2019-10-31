package com.test.project.modulecommon.base;

import android.app.Application;
import android.content.Context;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/30 16:23
 * Modify by
 * Modification date：
 * Modify content：
 */
public class BaseApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext() {
        return sContext;
    }
}
