package com.test.project.essayjoke.presenter;

import com.test.project.essayjoke.contract.EssayJokeMainContract;
import com.test.project.modulecommon.base.presenter.BasePresenter;
import com.test.project.modulecommon.utils.log.LogUtils;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/30 16:57
 * Modify by
 * Modification date：
 * Modify content：
 */
public class EssayJokeMainPresenter extends BasePresenter implements EssayJokeMainContract.IPresenter {
    private static final String TAG = "EassyJokeMainP";

    public EssayJokeMainPresenter(){

    }

    @Override
    public void start() {
        LogUtils.d(TAG,"Lifecycle EssayJokeMainPresenter start loadData");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG,"Lifecycle EssayJokeMainPresenter onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG,"Lifecycle EssayJokeMainPresenter onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,"Lifecycle EssayJokeMainPresenter onDestroy");
    }
}
