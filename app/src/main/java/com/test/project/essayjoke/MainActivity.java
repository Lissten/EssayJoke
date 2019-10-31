package com.test.project.essayjoke;

import android.widget.TextView;

import com.test.project.essayjoke.presenter.EssayJokeMainPresenter;
import com.test.project.modulecommon.base.BaseActivity;
import com.test.project.modulecommon.utils.log.LogUtils;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private EssayJokeMainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG,"Lifecycle MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(TAG,"Lifecycle MainActivity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,"Lifecycle MainActivity onDestroy");
    }

    @Override
    protected void initView() {
        mTextView= findViewById(R.id.test_tv);
        mTextView.setText("测试");
    }

    @Override
    protected void initData() {
        mPresenter = new EssayJokeMainPresenter();
        getLifecycle().addObserver(mPresenter);
    }

}
