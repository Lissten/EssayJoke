package com.test.project.essayjoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.test.project.modulecommon.annotation.ViewById;
import com.test.project.modulecommon.annotation.ViewUtilsImpl;

public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.test_tv)
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ViewUtilsImpl().inJect(this);

        mTextView.setText("IOCTest");
    }

}
