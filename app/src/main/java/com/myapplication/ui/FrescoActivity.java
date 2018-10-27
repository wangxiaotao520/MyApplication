package com.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.model.ModelEvent;
import com.myapplication.utils.fresco.FrescoUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Description:
 * Author: wangxiaotao
 * Create: 2018/6/9 0009 17:21
 */

public class FrescoActivity extends BaseActivity {

    SimpleDraweeView simpleDraweeView1;
    SimpleDraweeView simpleDraweeView2;
    SimpleDraweeView simpleDraweeView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        isStatusBar=true;
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initIntentData() {

    }

    @Override
    protected void initFragment() {
        //useless
    }

    @Override
    protected void initView() {

        simpleDraweeView1=findViewById(R.id.simpleDraweeview1);
        simpleDraweeView2=findViewById(R.id.simpleDraweeview2);
        simpleDraweeView3=findViewById(R.id.simpleDraweeview3);

    }

    @Override
    protected void initData() {
        String imgUrl= "http://static2.yulinapp.com/2018/0608/22/15284677751449942.jpg";
        FrescoUtils.getInstance().setImageUri(simpleDraweeView1,imgUrl);
        FrescoUtils.getInstance().setImageUri(simpleDraweeView2,imgUrl);
        FrescoUtils.getInstance().setImageUri(simpleDraweeView3,imgUrl);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fresco;
    }

    @Override
    protected int getFragmentCotainerId() {
        //useless
        return 0;
    }

    @Override
    public void finish() {
        ModelEvent modelEvent = new ModelEvent();
        modelEvent.setEvent("Hello! i am event");
        EventBus.getDefault().post(modelEvent);
        super.finish();
    }
}
