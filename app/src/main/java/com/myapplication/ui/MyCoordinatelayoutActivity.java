package com.myapplication.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.base.BaseFragment;
import com.myapplication.ui.fragment.FragmentCoordinatelayout1;
import com.myapplication.ui.fragment.FragmentCoordinatelayout2;

/**
 * Description:  coordinatelayout+appbarlayout +recyclerview的使用
 * created by wangxiaotao
 * 2019/8/3 0003 上午 10:50
 */
public class MyCoordinatelayoutActivity extends BaseActivity{


    private FrameLayout fl_bottom_home;
    private FrameLayout fl_bottom_my;
    private BaseFragment fragmentCoordinatelayout1;
    private BaseFragment fragmentCoordinatelayout2;

    @Override
    protected void initView() {
        fl_bottom_home = findViewById(R.id.fl_bottom_home);
        fl_bottom_my = findViewById(R.id.fl_bottom_my);

        fragmentCoordinatelayout1 = new FragmentCoordinatelayout1();
        fragmentCoordinatelayout2 = new FragmentCoordinatelayout2();
        switchFragmentNoBack(fragmentCoordinatelayout1);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        fl_bottom_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragmentNoBack(fragmentCoordinatelayout1);
            }
        });
        fl_bottom_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragmentNoBack(fragmentCoordinatelayout2);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coordinatelayout;
    }

    @Override
    protected void initIntentData() {

    }

    @Override
    protected int getFragmentCotainerId() {
        return R.id.fl_container;
    }

    @Override
    protected void initFragment() {

    }
}
