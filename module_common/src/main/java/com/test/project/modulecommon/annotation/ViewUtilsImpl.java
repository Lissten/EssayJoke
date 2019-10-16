package com.test.project.modulecommon.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/9 15:50
 * Modify by
 * Modification date：
 * Modify content：
 */
public class ViewUtilsImpl implements IViewUtils {

    @Override
    public void inJect(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    @Override
    public void inJect(View view) {
        inject(new ViewFinder(view), view);
    }

    @Override
    public void inJect(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    private void inject(ViewFinder finder, Object object) {
        //注入事件
        inJectEvent(finder, object);
        //注入属性
        inJectField(finder, object);
    }

    private void inJectField(ViewFinder finder, Object object) {
        if (null == object || null == finder) {
            return;
        }
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (null == viewById){
                continue;
            }
            int viewId = viewById.value();
            if (viewId <= 0) {
                continue;
            }
            View view = finder.findViewById(viewId);
            if (null == view) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(object, view);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void inJectEvent(ViewFinder finder, Object object) {
        if (null == object || null == finder) {
            return;
        }
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){

        }
    }

}
