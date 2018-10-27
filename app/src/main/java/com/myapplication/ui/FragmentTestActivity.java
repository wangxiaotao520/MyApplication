package com.myapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.ui.fragment.FragmentTest;
import com.myapplication.ui.fragment.FragmentTest2;
import com.myapplication.ui.fragment.FragmentTest3;

public class FragmentTestActivity extends BaseActivity implements View.OnClickListener {


    private TextView tv_fragment1;
    private TextView tv_fragment2;
    private TextView tv_fragment3;
    private FragmentTest fragmentTest1;
    private FragmentTest2 fragmentTest2;
    private FragmentTest3 fragmentTest3;

    @Override
    protected void initView() {
        tv_fragment1 = findViewById(R.id.tv_fragment1);
        tv_fragment2 = findViewById(R.id.tv_fragment2);
        tv_fragment3 = findViewById(R.id.tv_fragment3);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tv_fragment1.setOnClickListener(this);
        tv_fragment2.setOnClickListener(this);
        tv_fragment3.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_test;
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
        fragmentTest1 = new FragmentTest();
        Bundle bundle1 = new Bundle();
        bundle1.putString("type","1");
        fragmentTest1.setArguments(bundle1);
        fragmentTest2 = new FragmentTest2();
        Bundle bundle2 = new Bundle();
        bundle2.putString("type","2");
        fragmentTest2.setArguments(bundle2);
        fragmentTest3 = new FragmentTest3();
        Bundle bundle3 = new Bundle();
        bundle3.putString("type","3");
        fragmentTest3.setArguments(bundle3);
        switchFragmentNoBack(fragmentTest1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_fragment1:
                if (fragmentTest1!=null){
                    switchFragmentNoBack(fragmentTest1);
                }
                break;
            case R.id.tv_fragment2:
                if (fragmentTest2!=null){
                    switchFragmentNoBack(fragmentTest2);
                }
                break;
            case R.id.tv_fragment3:
                if (fragmentTest3!=null){
                    switchFragmentNoBack(fragmentTest3);
                }
                break;
                default:
                    break;
        }
    }
}
