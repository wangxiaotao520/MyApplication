package com.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.http.MyOkHttp;
import com.myapplication.http.response.RawResponseHandler;
import com.myapplication.model.ModelEvent;
import com.myapplication.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * 测试的Activity,也是启动的activity
 */
public class MainActivity extends BaseActivity {

    private TextView text_view;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化
      EventBus.getDefault().register(this);
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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        text_view = findViewById(R.id.text_view);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ArcProgressActivity.class));
                // requestData();
             //   startActivityForResult(new Intent(MainActivity.this,CityPickerActivity.class), RequestCodeInfo.GETCITY);
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
                text_view.setText(response+"");

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
