package com.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.base.BaseFragment;

/**
 * Description: coordinatelayout1  tab下方刷新
 * created by wangxiaotao
 * 2019/8/5 0005 上午 9:31
 */
public class FragmentCoordinatelayout1 extends BaseFragment{

    String[] mTitle = new String[8];
    String[] mData = new String[8];
    TabLayout mTabLayout;
    ViewPager mViewPager;
    @Override
    public void initView(View view) {
        initTab(view);

    }

    private void initTab(View view) {
        for (int i = 0; i < 8; i++) {
            mTitle[i] = "TAB" + (i + 1);
            mData[i] = "当前选中的是第" + (i + 1) + "Fragment";
        }
        mTabLayout = (TabLayout) view.findViewById(R.id.tl_tab);
        mViewPager = (ViewPager)view. findViewById(R.id.vp_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            //此方法用来显示tab上的名字
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position % mTitle.length];
            }

            @Override
            public Fragment getItem(int position) {
                //创建Fragment并返回
              //  TabFragment fragment = new TabFragment();
              //  fragment.setTitle(mData[position % mTitle.length]);

                FragmentRecyclerview1 fragment = new FragmentRecyclerview1();
                return fragment;
            }

            @Override
            public int getCount() {
                return mTitle.length;
            }
        });
        //将ViewPager关联到TabLayout上
        //   mTabLayout.setupWithViewPager(mViewPager);

        //自定义tabview
        for (int i = 0; i < 8; i++) {
            //1.支持添加字符串文本tab
            //tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));

            //2.支持添加图片tab
            //tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));

            //3.支持添加View
            View tabView = View.inflate(mActivity, R.layout.view_tab, null);
            ((TextView) tabView.findViewById(R.id.tab_item_textview)).setText("TAB" + i);
            ((TextView) tabView.findViewById(R.id.tab_item_textview)).setTextSize(16);
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(tabView));
        }

    }

    @Override
    public void initIntentData() {

    }

    @Override
    public void initListener() {
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //在选中的顶部标签时，为viewpager设置currentitem
                mViewPager.setCurrentItem(tab.getPosition());
                updateTabTextView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabTextView(tab, false);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coordinatelayout1;
    }

    /**
     * 字体加粗
     *
     * @param tab
     * @param isSelect
     */
    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {

        if (isSelect) {
            //选中加粗
            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
//            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setTextColor(getResources().getColor(R.color.colorPrimary));
            tabSelect.setTextSize(20);
            //         tabSelect.setText(tab.getText());
        } else {
            TextView tabUnSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
//            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setTextColor(getResources().getColor(R.color.colorAccent));
            tabUnSelect.setTextSize(16);
            //     tabUnSelect.setText(tab.getText());
        }
    }
}
