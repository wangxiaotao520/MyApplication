package com.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.http.MyOkHttp;
import com.myapplication.http.response.RawResponseHandler;
import com.myapplication.model.ModelEvent;
import com.myapplication.ui.adapter.MainAdapter;
import com.myapplication.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 测试的Activity,也是启动的activity
 */
public class MainActivity extends BaseActivity {


    private ListView list_view;
    private MainAdapter adapter;
    private List<String> mDatas ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        //初始化
      EventBus.getDefault().register(this);
    //  startActivities();
      //  asfd
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
        mDatas=new ArrayList<>();
        list_view = findViewById(R.id.list_view);
        adapter=new MainAdapter(this,mDatas);
        list_view.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mDatas.add("ArcProgressActivity");
        mDatas.add("ChartActivity");
        mDatas.add("CityPickerActivity");
        mDatas.add("FragmentTestActivity");
        mDatas.add("FrescoActivity");
        mDatas.add("FresconActivity");
        mDatas.add("GreenDaoActivity");
        mDatas.add("SmartRefreshActivity");
        mDatas.add("TabLayoutActivity");
        mDatas.add("MyCoordinatelayoutActivity");
        mDatas.add("NotificationActivity");
        mDatas.add("JNINativeLibActivity");

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String classStr = "com.myapplication.ui.";
                classStr=classStr+mDatas.get(position);
                Class clazz = null;
                try {
                    clazz = Class.forName(classStr);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this,clazz);
                startActivity(intent);
            }
        });

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentCotainerId() {
        //useless
        return 0;
    }

    //eventbus
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(ModelEvent event){
        ToastUtils.showShort(this.getApplicationContext(),"[got Event]"+event.getEvent());
    }

    private void requestData() {
        String url= "https://yulinapp.com/api.php?mod=Weibo&act=weibo_detail&oauth_token=67da3d967562ccdd79906145fb8757fb&oauth_token_secret=cfef8a4525d327f6d584b264521d439f&api_version=sociax_6.0";
        HashMap<String, String> params = new HashMap<>();
        params.put("feed_id","500565");
        MyOkHttp.get().post(url, params, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
              //  text_view.setText(response+"");

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
