package com.test.project.modulecommon.utils;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;

import com.test.project.modulecommon.base.BaseApplication;
import com.test.project.modulecommon.utils.log.ILogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Author: halo_huang
 * Create: 2018/4/18
 * Describe:
 */

public class ScreenLockUtils {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public static void dismissLockInterval() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(20)
                .filter(aLong -> ScreenLockUtils.isKeyguardShowingAndNotOccluded())
                .take(1)
                .flatMap(aLong -> Observable.timer(500, TimeUnit.MILLISECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> ScreenLockUtils.dismissLock());
    }

    @SuppressLint("PrivateApi")
    public static boolean isKeyguardShowingAndNotOccluded() {
        try {
            KeyguardManager km = (KeyguardManager) BaseApplication.getContext().getSystemService(Context.KEYGUARD_SERVICE);
            if (null == km) {
                return true;
            }
            Method method = KeyguardManager.class.getDeclaredMethod("isKeyguardShowingAndNotOccluded");
            method.setAccessible(true);
            return (boolean) method.invoke(km) || !km.inKeyguardRestrictedInputMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @SuppressLint("PrivateApi")
    public static void dismissLock() {
        try {
            KeyguardManager km = (KeyguardManager) BaseApplication.getContext().getSystemService(Context.KEYGUARD_SERVICE);
            Method method = KeyguardManager.class.getDeclaredMethod("dismissKeyguard");
            method.setAccessible(true);
            method.invoke(km);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
    }


    public static boolean isLock() {
        KeyguardManager km = (KeyguardManager) BaseApplication.getContext().getSystemService(Context.KEYGUARD_SERVICE);
        boolean u = km != null && km.inKeyguardRestrictedInputMode();
        ILogger.i("isLock" + u);
        return u;
    }

    public static boolean isOpenScreen() {
        PowerManager powerManager = (PowerManager) BaseApplication.getContext().getSystemService(Context.POWER_SERVICE);
        if (null == powerManager) {
            return true;
        }
        return powerManager.isScreenOn();
    }

    /**
     * 判断屏幕解锁是否加密
     *
     * @return
     */
    public static boolean checkPasswordToUnLock() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            KeyguardManager keyguardManager = (KeyguardManager) BaseApplication.getContext()
                    .getSystemService(Context.KEYGUARD_SERVICE);
            if (keyguardManager != null) {
                return keyguardManager.isKeyguardSecure();
            }
        } else {
            return isSecured();
        }
        return false;
    }

    private static boolean isSecured() {
        boolean isSecured;
        String classPath = "com.android.internal.widget.LockPatternUtils";
        try {
            Class<?> lockPatternClass = Class.forName(classPath);
            Object lockPatternObject = lockPatternClass.getConstructor(Context.class).newInstance(
                    BaseApplication.getContext());
            Method method = lockPatternClass.getMethod("isSecure");
            isSecured = (boolean) method.invoke(lockPatternObject);
        } catch (Exception e) {
            isSecured = false;
        }
        return isSecured;
    }

}
