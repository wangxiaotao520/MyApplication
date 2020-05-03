package com.myapplication.ui;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import com.myapplication.R;
import com.myapplication.base.BaseActivity;
import com.myapplication.utils.notification.ChannelEntity;
import com.myapplication.utils.notification.Constants;
import com.myapplication.utils.notification.ImportanceType;
import com.myapplication.utils.notification.Notifications;
import com.myapplication.utils.notification.NotifyManager;
import com.myapplication.utils.timer.CountDownTimer;

/**
 * Description:通知的demo 包括8.0以后的通知的封装
 *  created by wangxiaotao
 * 2020/4/27 0027 下午 5:50
 * //参考https://www.jianshu.com/p/9ea269e1832d
 *
 */
public class NotificationActivity extends BaseActivity implements View.OnClickListener{
    private Button mSimple;
    private Button mAction;
    private Button mRemoteInput;
    private Button mBigPictureStyle;
    private Button mBigTextStyle;
    private Button mInboxStyle;
    private Button mMediaStyle;
    private Button mMessagingStyle;
    private Button mProgress;
    private Button mCustomHeadsUp;
    private Button mCustom;
    private Button mClearAll;
    private NotifyManager notifyManager;
    private CountDownTimer countDownTimer;


    @Override
    protected void initView() {
        notifyManager =new NotifyManager(this);
        mSimple = findViewById(R.id.btn_simple);
        mAction = findViewById(R.id.btn_action);
        mRemoteInput = findViewById(R.id.btn_remote_input);
        mBigPictureStyle = findViewById(R.id.btn_big_picture_style);
        mBigTextStyle = findViewById(R.id.btn_big_text_style);
        mInboxStyle = findViewById(R.id.btn_inbox_style);
        mMediaStyle = findViewById(R.id.btn_media_style);
        mMessagingStyle = findViewById(R.id.btn_messaging_style);
        mProgress = findViewById(R.id.btn_progress);
        mCustomHeadsUp = findViewById(R.id.btn_custom_heads_up);
        mCustom = findViewById(R.id.btn_custom);
        mClearAll = findViewById(R.id.btn_clear_all);

        mSimple.setOnClickListener(this);
        mAction.setOnClickListener(this);
        mRemoteInput.setOnClickListener(this);
        mBigPictureStyle.setOnClickListener(this);
        mBigTextStyle.setOnClickListener(this);
        mInboxStyle.setOnClickListener(this);
        mMediaStyle.setOnClickListener(this);
        mMessagingStyle.setOnClickListener(this);
        mProgress.setOnClickListener(this);
        mCustomHeadsUp.setOnClickListener(this);
        mCustom.setOnClickListener(this);
        mClearAll.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                //Simple notification
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //8.0以上 通知首先要构造一个通知渠道
                    ChannelEntity chatChannel = new ChannelEntity(Constants.CHANNEL_LOW, this.getString(R.string.channel_low), ImportanceType.IMPORTANCE_HIGH);
                    chatChannel.setDescription("简单通知");
                    notifyManager.createNotificationGroupWithChannel(Constants.GROUP_NORMMAL, "group1", chatChannel);
                    Notifications.getInstance().sendSimpleNotificationAndroidO(this,notifyManager);
                }else {
                    Notifications.getInstance().sendSimpleNotification(this,notifyManager);
                }
                break;
            case R.id.btn_action:

                break;
            case R.id.btn_remote_input:

                break;
            case R.id.btn_big_picture_style:

                break;
            case R.id.btn_big_text_style:

                break;
            case R.id.btn_inbox_style:

                break;
            case R.id.btn_media_style:

                break;
            case R.id.btn_messaging_style:

                break;
            case R.id.btn_progress:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //8.0通知
                    ChannelEntity chatChannel = new ChannelEntity(Constants.CHANNEL_PROGRESS, this.getString(R.string.channel_progress), ImportanceType.IMPORTANCE_HIGH);
                    chatChannel.setDescription("进度通知");
                    //设置音量关掉
                    chatChannel.setSound(null);
                    chatChannel.setmAudioAttributes(null);
                    notifyManager.createNotificationGroupWithChannel(Constants.GROUP_NORMMAL, "group1", chatChannel);
                }else {
                }
                handlerTime(10*1000);

                break;
            case R.id.btn_custom_heads_up:

                break;
            case R.id.btn_custom:

                break;
            case R.id.btn_clear_all:

                break;
            default:
                //do nothing
        }
    }

    /**
     * 模拟一个下载（使用倒计时）
     * @param timeTmp
     *
     */
    private void handlerTime(long timeTmp) {

        if (countDownTimer != null) {
            //将复用的倒计时清除
            countDownTimer.cancel();
        }
        // 数据

        countDownTimer = new CountDownTimer(timeTmp, 1000) {
            public void onTick(long millisUntilFinished) {
               float progress= ((10*1000-millisUntilFinished)*100/(10*1000));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //8.0通知
                   Notifications.getInstance().sendProgressViewNotificationAndroidO(mContext,(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE), (int) progress);
                }else {
                    Notifications.getInstance().sendProgressViewNotification(mContext,(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE), (int) progress);
                }
            }

            public void onFinish(String redpackage_id) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //8.0通知
                    Notifications.getInstance().sendProgressViewNotificationAndroidO(mContext,(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE), 100);
                }else {
                    Notifications.getInstance().sendProgressViewNotification(mContext,(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE), 100);
                }
            }
        }.start();

    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
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
