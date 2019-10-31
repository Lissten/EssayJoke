package com.test.project.modulecommon.utils;

import android.annotation.SuppressLint;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.test.project.modulecommon.rxbus.RxBus;
import com.test.project.modulecommon.rxbus.bean.ActivityLifecycleBean;
import com.test.project.modulecommon.utils.log.ILogger;


public class ApplicationUtils {

    private static final String TAG = ApplicationUtils.class.getSimpleName();

    private static boolean mOnBackground = true;

    public static void postMsgOnForeground() {
        if (mOnBackground) {
            RxBus.getInstance().post(new ActivityLifecycleBean(ActivityLifecycleBean.LifecycleCode.ON_FOREGROUND));
        }
        mOnBackground = false;
    }

    public static void postMsgOnBackground() {
        mOnBackground = true;
        RxBus.getInstance().post(new ActivityLifecycleBean(ActivityLifecycleBean.LifecycleCode.ON_BACKGROUND));
    }

    @SuppressLint("InlinedApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean isForeground(Context context) {
        long time = System.currentTimeMillis();
        String packageName = context.getPackageName();
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);

        if(usageStatsManager == null){
            return false;
        }

        UsageEvents usageEvents = usageStatsManager.queryEvents(time - 10000, time);
        UsageEvents.Event event = new UsageEvents.Event();
        String currentPackageName = packageName;
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event);
            if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                currentPackageName = event.getPackageName();
                ILogger.d(TAG, "foreground packageName: " + currentPackageName);
            }
        }
        return !TextUtils.isEmpty(packageName) && packageName.equals(currentPackageName);
    }
}
