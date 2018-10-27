package com.myapplication.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.base.BaseFragment;

/**
 * Description:
 * Author: wangxiaotao
 * Create: 2018/6/11 18:19
 */
public class FragmentTest extends BaseFragment{

    private TextView tv_fragment_name;
    private String type;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        type = bundle.getString("type");

    }

    @Override
    public void initView(View view) {
        tv_fragment_name = view.findViewById(R.id.tv_fragment_name);
    }

    @Override
    public void initIntentData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tv_fragment_name.setText("Fragment"+type);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }
}
