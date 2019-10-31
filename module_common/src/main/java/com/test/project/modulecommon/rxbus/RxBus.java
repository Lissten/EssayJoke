package com.test.project.modulecommon.rxbus;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * PublishSubject: 只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
 *
 * @author zhangshao
 * @desc
 * @time 2019/4/18 10:19
 */
public class RxBus {
    private static volatile RxBus mInstance;
    private final Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送事件
     */
    public void post(Object event) {
        mBus.onNext(event);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> toObservable(final Class<T> eventType) {
        return mBus.ofType(eventType);
    }

    /**
     * 不指定类型，返回Object类型的Observable对象
     *
     * @return
     */
    public Observable<Object> toSubscriber() {
        return mBus;
    }

    /**
     * 根据传入对象类型返回对应类型的Observable对象
     *
     * @param <T>  返回类型
     * @param type 传入类型
     * @return
     */
    public <T> Observable<T> toSubscriber(Class<T> type) {
        return mBus.ofType(type).compose(ioMain());
    }

    public <T> ObservableTransformer<T, T> ioMain() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public void reset() {
        mInstance = null;
    }

    /**
     * 生成可停止的订阅事件
     * @param type 数据类型（Bean）
     * @param next 成功时的订阅事件
     * @param error 失败时的订阅事件
     * @param <T> 数据类型
     * @return 事件
     */
    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return toObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }
}
