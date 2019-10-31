package com.test.project.modulecommon.observer;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Author    zhouchuan
 * Describe：Lifecycle 处理Activity或者Fragment组件的生命周期
 * <pre>
 *     可以通过绑定P层，实现Activity的生命周期变化，Presenter能够感知
 * </pre>
 * Data:      2019/10/30 16:46
 * Modify by
 * Modification date：
 * Modify content：
 */
public interface IEssayJokeObsever extends LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();
}
