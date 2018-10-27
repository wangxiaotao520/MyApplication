package com.myapplication.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.ui.fragment.TabFragment;

/**
 * Description:写一个关于tablayout的demo
 * created by wangxiaotao
 * 2018/6/22 0022 下午 3:37
 */
public class TabLayoutActivity extends BaseActivity {

    String[] mTitle = new String[20];
    String[] mData = new String[20];
    TabLayout mTabLayout;
    ViewPager mViewPager;
    @Override
    protected void initView() {
        for (int i = 0; i < 20; i++) {
            mTitle[i] = "TAB" + (i + 1);
            mData[i] = "当前选中的是第" + (i + 1) + "Fragment";
        }
        mTabLayout = (TabLayout) findViewById(R.id.tl_tab);
        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //此方法用来显示tab上的名字
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position % mTitle.length];
            }

            @Override
            public Fragment getItem(int position) {
                //创建Fragment并返回
                TabFragment fragment = new TabFragment();
                fragment.setTitle(mData[position % mTitle.length]);
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
        for (int i = 0; i < 20; i++) {
            //1.支持添加字符串文本tab
            //tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));

            //2.支持添加图片tab
            //tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));

            //3.支持添加View
            View tabView = View.inflate(TabLayoutActivity.this, R.layout.view_tab, null);
            ((TextView) tabView.findViewById(R.id.tab_item_textview)).setText("TAB" + i);
            ((TextView) tabView.findViewById(R.id.tab_item_textview)).setTextSize(16);
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(tabView));
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
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
    protected int getLayoutId() {
        return R.layout.layout_tablayout;
    }

    @Override
    protected void initIntentData() {

    }

    @Override
    protected int getFragmentCotainerId() {
        return 0;
    }

    @Override
    protected void initFragment() {

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
