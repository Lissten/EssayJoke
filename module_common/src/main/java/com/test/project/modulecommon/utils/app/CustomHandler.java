package com.test.project.modulecommon.utils.app;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.test.project.modulecommon.utils.log.ILogger;

/**
 * Author    zhouchuan
 * Describe：自定义Handler，解决Handler在某些情况下空指针造成崩溃
 * Data:      2019/10/30 16:07
 * Modify by
 * Modification date：
 * Modify content：
 */
public class CustomHandler extends Handler {
    private static final String TAG = CustomHandler.class.getSimpleName();

    public CustomHandler() {
        super();
    }

    public CustomHandler(Callback callback) {
        super(callback);
    }

    public CustomHandler(Looper looper) {
        super(looper);
    }

    public CustomHandler(Looper looper, Callback callback) {
        super(looper, callback);
    }

    @Override
    public void dispatchMessage(Message msg) {
        try {
            super.dispatchMessage(msg);
        } catch (Exception e) {
            ILogger.w(TAG, " dispatchMessage error: " + e);
        }
    }

    @Override
    public void handleMessage(Message msg) {
        try {
            super.handleMessage(msg);
        } catch (Exception e) {
            ILogger.w(TAG, " handleMessage error: " + e);
        }
    }
}
