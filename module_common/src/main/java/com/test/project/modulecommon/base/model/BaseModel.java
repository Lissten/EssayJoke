package com.test.project.modulecommon.base.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;

import com.test.project.modulecommon.base.callback.ResultCallBack;
import com.test.project.modulecommon.utils.log.ILogger;

/**
 * Desc:   Model的基础类，所有的model都继承了这个model并实现最基本的方法
 */
public abstract  class BaseModel implements IBaseModel {

    public static final int MESSAGE_POST_SUCCESS = 1;
    public static final int MESSAGE_POST_ERROR = 2;

    protected Handler mMainHandler;
    protected boolean mIsDestroy = false;
   
    public BaseModel(){
        mIsDestroy = false;
    }

    public Handler getMainHandler(){
        if(mMainHandler == null){
            synchronized (this){
                if(mMainHandler == null){
                    mMainHandler = new InternalHandler();
                }
            }
        }
        return mMainHandler;
    }

    /**
     * 结束，销毁对象
     * <pre>
     *     调用此方法用，{@link #postSuccess(ResultCallBack, Object)} 和 {@link #postError(ResultCallBack, Exception, int)} 将不会回调
     * </pre>
     */
    @CallSuper
    public void onDestroy(){
        mIsDestroy = true;
        if(mMainHandler != null){
            mMainHandler.removeCallbacksAndMessages(null);
            mMainHandler = null;
        }
    }

    public <T> void postSuccess(ResultCallBack<T> callBack, T data){
        if(mIsDestroy){
            return;
        }
        if(callBack == null){
            return;
        }
        Message msg = getMainHandler().obtainMessage(MESSAGE_POST_SUCCESS);
        Result<T> result = new Result<>(callBack, null, 1, data);
        msg.obj = result;
        msg.sendToTarget();
    }

    public <T> void postError(ResultCallBack<T> callBack, Exception e, int errorCode){
        if(mIsDestroy){
            return;
        }
        if(callBack == null){
            return;
        }
        Message msg = getMainHandler().obtainMessage(MESSAGE_POST_ERROR);
        Result<T> result = new Result<>(callBack, e,  errorCode);
        msg.obj = result;
        msg.sendToTarget();
    }

    public static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            Result<?> result = (Result<?>) msg.obj;

            if(result == null || result.mCallback == null){
                ILogger.w("result data or callback is null!");
                return;
            }

            switch (msg.what) {
                case MESSAGE_POST_SUCCESS:
                    // There is only one result
                    result.mCallback.onSuccess(result.mData[0]);
                    break;
                case MESSAGE_POST_ERROR:
                    result.mCallback.onError(result.mException, msg.arg1);
                    break;
                default:
                    break;
            }
        }
    }

    public static class Result<Data> {
        final ResultCallBack mCallback;
        final Data[] mData;
        final Exception mException;
        final int mResultError;

        Result(ResultCallBack callBack, Exception e, int resultCode, Data... data) {
            mCallback = callBack;
            mData = data;
            mException = e;
            mResultError = resultCode;
        }
    }
}
