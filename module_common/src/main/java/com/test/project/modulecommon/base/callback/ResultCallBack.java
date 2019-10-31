package com.test.project.modulecommon.base.callback;

/**
 * model结果回调函数,样板
 * <p/>
 * <pre>
 * {@link #onSuccess(Object)}
 *
 * {@link #onError(Exception, int)}
 * </pre>
 *
 * @author llp
 *         <p/>
 *         2015年10月15日 下午6:52:29
 */
public interface ResultCallBack<T> {

    /**
     * 正确结果
     * @param result 结果
     */
    void onSuccess(T result);

    /**
     * 异常
     * @param exception 异常
     * @param errorCode 错误码
     */
    void onError(Exception exception, int errorCode);

}
