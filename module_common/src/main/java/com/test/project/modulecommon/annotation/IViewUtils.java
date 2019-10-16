package com.test.project.modulecommon.annotation;

import android.app.Activity;
import android.view.View;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/9 15:48
 * Modify by
 * Modification date：
 * Modify content：
 */
public interface IViewUtils {

    void inJect(Activity activity);

    void inJect(View view);

    void inJect(View view, Object object);

}
