package com.test.project.modulecommon.utils.viewutils;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.test.project.modulecommon.annotation.CheckNet;
import com.test.project.modulecommon.annotation.OnClick;
import com.test.project.modulecommon.annotation.ViewInject;
import com.test.project.modulecommon.utils.app.NetUtils;
import com.test.project.modulecommon.utils.app.ToastUtils;
import com.test.project.modulecommon.utils.tools.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/16 11:28
 * Modify by
 * Modification date：
 * Modify content：
 */
public class ViewInjectorImpl implements ViewInjector {
    private static final String TAG = "ViewInjectorImpl";

    private static final HashSet<Class<?>> IGNORED = new HashSet<>();

    static {
        IGNORED.add(Object.class);
        IGNORED.add(Activity.class);
        IGNORED.add(android.app.Fragment.class);
        try {
            IGNORED.add(Class.forName("android.support.v4.app.Fragment"));
            IGNORED.add(Class.forName("android.support.v4.app.FragmentActivity"));
        } catch (Throwable ignored) {
            Log.e(TAG, "ViewInjectorImpl ignored message = " + ignored.getMessage());
        }
    }

    private static final Object lock = new Object();
    private static volatile ViewInjectorImpl mInstance;

    private ViewInjectorImpl() {

    }

    public static void registerInstance() {
        if (null == mInstance) {
            synchronized (lock) {
                if (null == mInstance) {
                    mInstance = new ViewInjectorImpl();
                }
            }
        }
        x.Ext.setViewInjector(mInstance);
    }

    @Override
    public void inJect(Activity activity) {
        Class<?> handlerType = activity.getClass();
        inJectObject(activity, handlerType, new ViewFinder(activity));
    }

    @Override
    public void inJect(View view) {
        inJectObject(view, view.getClass(), new ViewFinder(view));
    }

    @Override
    public void inJect(Object handler, View view) {
        inJectObject(handler, handler.getClass(), new ViewFinder(view));
    }

    //    @Override
    //    public View inJect(Object fragment, LayoutInflater inflater, ViewGroup container) {
    //
    //    }

    private void inJectObject(Object handler, Class<?> handlerType, ViewFinder finder) {
        if (handlerType == null || IGNORED.contains(handlerType) || handlerType.getName().startsWith("androidx.")) {
            return;
        }
        Log.i(TAG,"inJectObject");
        // 从父类到子类递归
        inJectObject(handler, handlerType.getSuperclass(), finder);

        //inject view
        Field[] fields = handlerType.getDeclaredFields();
        if (null != fields && fields.length > 0) {
            for (Field field : fields) {
                Class<?> type = field.getType();
                //不注入静态字段、final修饰字段、基本数据类型、数组
                if (Modifier.isStatic(type.getModifiers()) ||
                    Modifier.isFinal(field.getModifiers()) ||
                    type.isPrimitive() ||
                    type.isArray()
                ) {
                    continue;
                }
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                if (null != viewInject) {
                    try {
                        View view = finder.findViewById(viewInject.value(), viewInject.parentId());
                        if (null != view) {
                            field.setAccessible(true);
                            field.set(handler, view);
                        } else {
                            throw new RuntimeException("Invalid @ViewInject for "
                                + handlerType.getSimpleName() + "." + field.getName());
                        }
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            }
        }// end inject view

        //inject Event
        //1、获取类里面的所有方法
        Method[] methods = handlerType.getDeclaredMethods();
        if (null != methods && methods.length > 0) {
            for (Method method : methods) {
                if (Modifier.isPrivate(method.getModifiers()) ||
                    Modifier.isStatic(method.getModifiers())
                ) {
                    continue;
                }
                //2、获取OnClick里面的value值
                OnClick onClick = method.getAnnotation(OnClick.class);
                try {
                    if (null != onClick) {
                        int[] viewIds = onClick.value();
                        for (int viewId : viewIds) {
                            if (viewId > 0) {
                                View view = finder.findViewById(viewId);
                                if (null == view) {
                                    continue;
                                }
                                //扩展功能，检测网络
                                CheckNet checkNet = method.getAnnotation(CheckNet.class);
                                String tipMsg = "";
                                if (null != checkNet){
                                    tipMsg = checkNet.value();
                                }
                                view.setOnClickListener(new DeclaredOnClickListener(method, handler,tipMsg));
                            }
                        }
                    }
                } catch (Throwable throwable) {
                    Log.e(TAG, throwable.getMessage());
                }
            }
        }
    }

    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object mHandler;
        private String mTipMsg;

        public DeclaredOnClickListener(Method method, Object handler, String tipMsg) {
            this.mMethod = method;
            this.mHandler = handler;
            this.mTipMsg = tipMsg;
        }

        @Override
        public void onClick(View v) {
            if (!StringUtils.isEmpty(mTipMsg)){
                boolean connected = NetUtils.isConnected(v.getContext());
                if (!connected){
                    ToastUtils.getInstance(v.getContext()).s(mTipMsg);
                    return;
                }
            }
            try {
                mMethod.setAccessible(true);
                mMethod.invoke(mHandler,v);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(
                    "Could not execute non-public method for ViewInjectorImpl:onClick", e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(
                    "Could not execute method for ViewInjectorImpl:onClick", e);
            }
        }
    }
}
