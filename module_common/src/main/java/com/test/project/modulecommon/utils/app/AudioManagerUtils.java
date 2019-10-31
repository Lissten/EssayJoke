package com.test.project.modulecommon.utils.app;

import android.content.Context;
import android.media.AudioManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Author    zhouchuan
 * Describe：音频焦点工具类
 * Data:      2019/10/30 15:51
 * Modify by
 * Modification date：
 * Modify content：
 */
public class AudioManagerUtils {
    private static final String TAG = AudioManagerUtils.class.getSimpleName();
    private static AudioManagerUtils sInstance;
    private final AudioManager mAudioManager;
    private Map<String, OnAudioFocusChangeListenerUtil> mListenerMap = new HashMap<>();

    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                pauseCallback();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN || focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) {
                playCallback();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                pauseCallback();
            }
        }
    };

    private void playCallback() {
        if (null == mListenerMap || mListenerMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, OnAudioFocusChangeListenerUtil> entry : mListenerMap.entrySet()) {
            OnAudioFocusChangeListenerUtil listener = entry.getValue();
            if (null != listener) {
                listener.play();
            }
        }
    }

    private void pauseCallback() {
        if (null == mListenerMap || mListenerMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, OnAudioFocusChangeListenerUtil> entry : mListenerMap.entrySet()) {
            OnAudioFocusChangeListenerUtil listener = entry.getValue();
            if (null != listener) {
                listener.pause();
            }
        }
    }

    /**
     * 获取音频焦点
     * */
    public void requestAudioFocus(){
        if (null == mAudioManager){
            return;
        }
        mAudioManager.requestAudioFocus(mAudioFocusListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    }

    /**
     * 释放音频焦点
     */
    public void abandonAudioFocus(){
        if (mAudioManager == null){
            return;
        }
        mAudioManager.abandonAudioFocus(mAudioFocusListener);
    }

    public OnAudioFocusChangeListenerUtil getOnAudioFocusChangeListenerUtil(String tag) {
        return null == mListenerMap ? null : mListenerMap.get(tag);
    }

    public AudioManagerUtils(Context context) {
        mAudioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    public static AudioManagerUtils getInstance(Context context) {
        if (null == sInstance) {
            synchronized (AudioManagerUtils.class) {
                if (null == sInstance) {
                    sInstance = new AudioManagerUtils(context);
                }
            }
        }
        return sInstance;
    }

    public interface OnAudioFocusChangeListenerUtil {
        void play();

        void pause();
    }

    public void setOnAudioFocusListener(String tag, OnAudioFocusChangeListenerUtil onAudioFocusListener) {
        if (null == mListenerMap) {
            mListenerMap = new HashMap<>();
        }
        if (null == onAudioFocusListener) {
            if (!mListenerMap.isEmpty()) {
                mListenerMap.remove(tag);
            }
            return;
        }
        mListenerMap.put(tag, onAudioFocusListener);
    }
}
