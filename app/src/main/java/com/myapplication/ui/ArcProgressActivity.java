package com.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.czp.library.ArcProgress;
import com.myapplication.R;

/**
 * Description:进度条
 * created by wangxiaotao
 * 2018/9/20 0020 下午 3:18
 */
public class ArcProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_progress);
        ArcProgress progress = findViewById(R.id.myProgress1);


    }
}
