package com.test.project.modulecommon.utils.viewutils;

import android.app.Activity;
import android.view.View;

/**
 * Author    zhouchuan
 * Describe： View注入接口
 * Data:      2019/10/9 15:48
 * Modify by
 * Modification date：
 * Modify content：
 */
public interface ViewInjector {

    /**
     * 注入Activity
     */
    void inJect(Activity activity);

    /**
     * 注入View
     */
    void inJect(View view);

    /**
     * 注入View holder
     *
     * @param handler view holder
     */
    void inJect(Object handler, View view);

//    /**
//     * 注入Fragment
//     */
//    View inJect(Object fragment, LayoutInflater inflater, ViewGroup container);

}
