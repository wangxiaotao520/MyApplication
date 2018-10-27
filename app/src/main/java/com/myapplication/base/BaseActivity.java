package com.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Description:基类Activity
 * Author: wangxiaotao
 * Create: 2018/6/10 16:40
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    /**
     * 是否沉浸状态栏
     **/
    protected boolean isStatusBar = false;
    /**
     * 是否允许全屏
     **/
    protected boolean isFullScreen = false;
    /**
     * 是否允许旋转屏幕
     **/
    protected boolean isScreenRoate = false;

    // 监听当前fragment
    private BaseFragment currentFragment;

    /**
     * context
     **/
    protected Context mContext;
    /**
     * 是否输出日志信息
     **/
    private boolean isDebug;

    /**
     * 初始化界面
     **/
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定事件
     */
    protected abstract void initListener();

    /**
     * 布局
     * @return
     */
    protected abstract int getLayoutId();


    private ScreenManager screenManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager = ScreenManager.getInstance();
        screenManager.setStatusBar(isStatusBar, this);
        screenManager.setScreenRoate(isScreenRoate, this);
        screenManager.setFullScreen(isFullScreen, this);
        setContentView(getLayoutId());
        ActivityStackManager.getActivityStackManager().pushActivity(this);
        mContext = this;
        initIntentData();
        initView();
        initFragment();
        initListener();
        initData();
        //各种初始化：方法初始化、通用配置、

    }

    /**
     * 初始化intent
     */
    protected abstract void initIntentData() ;
    /**
     * 获取Fragment的container的id(有需要使用Fragment时)
     */
    protected abstract int getFragmentCotainerId();
    /**
     * 如果有需要使用Fragment就在这里初始化Fragment
     */
    protected abstract void initFragment();
    /**
     * Method: fragment切换(Fragment的名字不能相同)
     * Creator: LiYing
     * Date: 2017/3/22 0022 下午 3:54
     */
    protected void switchFragmentNoBack(BaseFragment fragmentInstance) {
        currentFragment = fragmentInstance;
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (int i = 0; i < fragments.size(); i++) {
                BaseFragment tempFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragments.get(i).getClass().getName());
                if (tempFragment != null) {
                    if (tempFragment.getClass().getName().equals(fragmentInstance.getClass().getName())) {
                        t.show(tempFragment);
                    } else {
                        t.hide(tempFragment);
                    }
                }
            }
        }
        Fragment fragmentTarget = getSupportFragmentManager().findFragmentByTag(fragmentInstance.getClass().getName());
        if (fragmentTarget == null && !fragmentInstance.isAdded()) {
            t.add(getFragmentCotainerId(), fragmentInstance, fragmentInstance.getClass().getName());
        }
        t.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }
    /**
     * 退出应用
     * called while exit app.
     */
    public void exit() {
        ActivityStackManager.getActivityStackManager().finishAllActivity();//remove all activity.
        System.exit(0);//system exit.
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 一些销毁  等等

        super.onDestroy();
        ActivityStackManager.getActivityStackManager().popActivity(this);
    }
}
