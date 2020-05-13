package com.myapplication.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.web.god.todayinformationndk.MainActivity;

/**
 * Description: 在java下调用so库
 * created by wangxiaotao
 * 2020/5/13 0013 16:46
 * 本Acvtivity参考的是网易云课堂下的ndk调用的demo
 *
 *
 * 注意，要在在build.gradle下添加：
 *
 *  sourceSets{
 *         main{
 *             jniLibs.srcDirs = ['libs']
 *         }
 *     }
 *
 *  如果架构还有问题要添加
 * ndk {
 *             //选择要添加的对应 cpu 类型的 .so 库。
 *             abiFilters 'x86', 'armeabi-v7a'
 *             // 还可以添加 'x86', 'x86_64', 'mips', 'mips64'
 *         }
 *
 * 可以参考https://www.jianshu.com/p/27de58017a71，也可以参考网易云课堂ason老师讲得
 */
public class JNINativeLibActivity extends BaseActivity {
    static {
        System.loadLibrary("native-lib");
    }

    private TextView tv_jni;
    private Button btn_jni;

    @Override
    protected void initView() {
        tv_jni = findViewById(R.id.tv_jni);
        btn_jni = findViewById(R.id.btn_jni);
    }

    @Override
    protected void initData() {

    }

    /**
     * 为什么要新建一个包
     *
     * 注意几个地方，一、包名要和so库中的包名一样；二、类名也要一致。
     * 我们看看so库的头文件中的函数：
     *
     * 函数名为Java_包名类名函数名。所以我们创建的Android工程的包名也要一致，即cn_scnu，同时，负责加载并且提供native方法的类的类名也要相同
     * 即MainActivity，否则调用不成功，提示找不到方法。
     *

     *
     */
    @Override
    protected void initListener() {
        btn_jni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tv_jni.setText(MainActivity.stringFromJNI());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jni_native_lib;
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
}
