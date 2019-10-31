package com.test.project.modulecommon.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;

import com.test.project.modulecommon.utils.ApplicationUtils;
import com.test.project.modulecommon.utils.CommonThreadPool;
import com.test.project.modulecommon.utils.ScreenLockUtils;
import com.test.project.modulecommon.utils.app.AudioManagerUtils;
import com.test.project.modulecommon.utils.app.CustomHandler;
import com.test.project.modulecommon.utils.log.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Author    zhouchuan
 * Describe：Activity基类，对音频焦点做了统一处理
 * Data:      2019/10/30 15:45
 * Modify by
 * Modification date：
 * Modify content：
 */
public abstract class BaseActivity extends FragmentActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private CustomHandler mHandler = new CustomHandler();
    private static final Object ABANDON_AUDIO_FOCUS_LOCK = new Object();
    private static Runnable sAbandonAudioFocusTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needLoadLayout()) {
            startLoadLayout();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestAudioFocus();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        abandonAudioFocus();
        super.onStop();
    }

    private void requestAudioFocus() {
        post(() -> {
            LogUtils.d(TAG,"requestAudioFocus");
            AudioManagerUtils.getInstance(getApplicationContext()).requestAudioFocus();
        });
    }

    private void abandonAudioFocus(){
        synchronized (ABANDON_AUDIO_FOCUS_LOCK){
            if(sAbandonAudioFocusTask == null){
                sAbandonAudioFocusTask = new AbandonAudioFocusRunnable(this.getApplicationContext());
            }
            CommonThreadPool.getTaskThreadPool().remove(sAbandonAudioFocusTask);
            CommonThreadPool.getTaskThreadPool().execute(sAbandonAudioFocusTask);
        }
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public void post(Runnable runnable){
        if (isFinishing()){
            return;
        }
        mHandler.post(runnable);
    }

    private static class AbandonAudioFocusRunnable implements Runnable {
        private WeakReference<Context> appContextWf;

        AbandonAudioFocusRunnable(Context appContext) {
            this.appContextWf = new WeakReference<>(appContext);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void run() {
            Context context = appContextWf == null ? null : appContextWf.get();
            try {
                if(context != null && (!ApplicationUtils.isForeground(context) || !ScreenLockUtils.isOpenScreen())) {
                    // 释放音频焦点
                    LogUtils.i(TAG, "App is background, abandon audio focus");
                    ApplicationUtils.postMsgOnBackground();
                    AudioManagerUtils.getInstance(context).abandonAudioFocus();
                }
            } catch (Exception e) {
                LogUtils.w(TAG, "abandonAudioFocus-error", e);
            }
        }
    }

    protected void startLoadLayout() {
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected boolean needLoadLayout() {
        return true;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();


}
