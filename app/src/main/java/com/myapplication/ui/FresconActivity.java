package com.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myapplication.R;
import com.myapplication.utils.fresco.FrescoUtils;

/**
 * Description:
 * Author: wangxiaotao
 * Create: 2018/6/9 0009 17:21
 */

public class FresconActivity extends AppCompatActivity {
    SimpleDraweeView simpleDraweeView1;
    SimpleDraweeView simpleDraweeView2;
    SimpleDraweeView simpleDraweeView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        String imgUrl= "http://static2.yulinapp.com/2018/0608/22/15284677751449942.jpg";
        simpleDraweeView1=findViewById(R.id.simpleDraweeview1);
        simpleDraweeView2=findViewById(R.id.simpleDraweeview2);
        simpleDraweeView3=findViewById(R.id.simpleDraweeview3);
        FrescoUtils.getInstance().setImageUri(simpleDraweeView1,imgUrl);
        FrescoUtils.getInstance().setImageUri(simpleDraweeView2,imgUrl);
        FrescoUtils.getInstance().setImageUri(simpleDraweeView3,imgUrl);
        //haohao
    }
}
