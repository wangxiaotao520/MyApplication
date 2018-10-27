package com.myapplication;

import android.app.Application;
import android.content.Context;

import com.myapplication.utils.fresco.FrescoUtils;

/**
 * Description: 基类application
 * Author: wangxiaotao
 * Create: 2018/6/9 0009 10:34
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = this.getApplicationContext();
        super.onCreate();
        //初始化fresco
        FrescoUtils.getInstance().initializeFresco(this);
    }

    public static Context getContext() {
        return mContext;
    }
    //设置SmartRefreshLayout
//    static {
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(20);
//            }
//        });
//    }
}
