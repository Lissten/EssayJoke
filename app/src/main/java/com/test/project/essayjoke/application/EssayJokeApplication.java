package com.test.project.essayjoke.application;

import com.test.project.essayjoke.BuildConfig;
import com.test.project.modulecommon.base.BaseApplication;
import com.test.project.modulecommon.utils.log.LogUtils;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/30 16:21
 * Modify by
 * Modification date：
 * Modify content：
 */
public class EssayJokeApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    private void initLog() {
        LogUtils.initLog(new LogUtils
            .Builder()
            .setDebug(BuildConfig.DEBUG)
            .tagPrefix("EssJoke_")
            .methodCount(0)
        );
    }
}
