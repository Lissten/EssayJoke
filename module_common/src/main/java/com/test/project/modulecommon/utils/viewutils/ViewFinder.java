package com.test.project.modulecommon.utils.viewutils;

import android.app.Activity;
import android.view.View;

/**
 * Author    zhouchuan
 * Describe：
 * Data:      2019/10/9 15:50
 * Modify by
 * Modification date：
 * Modify content：
 */
final class ViewFinder {

    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    public ViewFinder(View view) {
        this.mView = view;
    }

    public View findViewById(int viewId) {
        if (viewId <= 0) {
            return null;
        }
        if (null != mActivity) {
            return mActivity.findViewById(viewId);
        }
        if (null != mView) {
            return mView.findViewById(viewId);
        }
        return null;
    }

    public View findViewById(int id, int pid) {
        View pView = null;
        if (pid > 0) {
            pView = this.findViewById(pid);
        }

        View view = null;
        if (pView != null) {
            view = pView.findViewById(id);
        } else {
            view = this.findViewById(id);
        }
        return view;
    }
}

