package com.test.project.modulecommon.utils.log;

import com.test.project.modulecommon.BuildConfig;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/16 16:24
 * Modify by
 * Modification date：
 * Modify content：
 */
public class SDKVersion {

    /**
     * 获取库名称
     * @return 库名称
     */
    public static String getLibraryName(){
        return BuildConfig.APPLICATION_ID;
    }

    /**
     * 构建时的版本值，如：1, 2, 3, ...
     */
    public static int getSDKInt() {
        return BuildConfig.VERSION_CODE;
    }

    /**
     * 版本名称，如：1.0.0, 2.1.2-alpha, ...
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }
}
