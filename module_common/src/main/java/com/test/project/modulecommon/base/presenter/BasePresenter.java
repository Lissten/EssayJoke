package com.test.project.modulecommon.base.presenter;


import com.test.project.modulecommon.utils.log.ILogger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BasePresenter implements IBasePresenter {

    private CompositeDisposable mCompositeDisposable;

    @Override
    public void start() {

    }

    protected void addRxSubscribe(Disposable disposable) {
        if (disposable == null){
            return;
        }
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void deleteRxSubscribe(Disposable disposable) {
        if (null != disposable && null != mCompositeDisposable && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.delete(disposable);
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        if (null != mCompositeDisposable) {
            mCompositeDisposable.clear();
        }
        ILogger.d("BPManager", "BasePresenter");
    }
}
