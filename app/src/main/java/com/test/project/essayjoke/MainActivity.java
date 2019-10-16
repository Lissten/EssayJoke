package com.test.project.essayjoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.project.modulecommon.annotation.CheckNet;
import com.test.project.modulecommon.annotation.OnClick;
import com.test.project.modulecommon.annotation.ViewInject;
import com.test.project.modulecommon.utils.viewutils.x;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.test_tv)
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inJect(this);
        mTextView.setText("测试");
    }

    @OnClick({R.id.test_tv})
    @CheckNet("亲，请检查你的网络哟！")
    public void onClick(View view){
        Toast.makeText(this,"点击测试",Toast.LENGTH_SHORT).show();
    }
}
