package com.myapplication.utils.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.myapplication.R;
import com.myapplication.ui.NotificationService;

/**
 * Description: 通知相关类
 * created by wangxiaotao
 * 2020/4/29 0029 下午 3:34
 */
public class Notifications {



    public final static String ACTION_SIMPLE = "com.android.peter.notificationdemo.ACTION_SIMPLE";
    public final static String ACTION_ACTION = "com.android.peter.notificationdemo.ACTION_ACTION";
    public final static String ACTION_REMOTE_INPUT = "com.android.peter.notificationdemo.ACTION_REMOTE_INPUT";
    public final static String ACTION_BIG_PICTURE_STYLE = "com.android.peter.notificationdemo.ACTION_BIG_PICTURE_STYLE";
    public final static String ACTION_BIG_TEXT_STYLE = "com.android.peter.notificationdemo.ACTION_BIG_TEXT_STYLE";
    public final static String ACTION_INBOX_STYLE = "com.android.peter.notificationdemo.ACTION_INBOX_STYLE";
    public final static String ACTION_MEDIA_STYLE = "com.android.peter.notificationdemo.ACTION_MEDIA_STYLE";
    public final static String ACTION_MESSAGING_STYLE = "com.android.peter.notificationdemo.ACTION_MESSAGING_STYLE";
    public final static String ACTION_PROGRESS = "com.android.peter.notificationdemo.ACTION_PROGRESS";
    public final static String ACTION_SEND_PROGRESS_NOTIFICATION = "com.android.peter.notificationdemo.ACTION_SEND_PROGRESS_NOTIFICATION";
    public final static String ACTION_CUSTOM_HEADS_UP_VIEW = "com.android.peter.notificationdemo.ACTION_CUSTOM_HEADS_UP_VIEW";
    public final static String ACTION_CUSTOM_VIEW = "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW";
    public final static String ACTION_CUSTOM_VIEW_OPTIONS_LOVE = "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_LOVE";
    public final static String ACTION_CUSTOM_VIEW_OPTIONS_PRE = "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_PRE";
    public final static String ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE = "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE";
    public final static String ACTION_CUSTOM_VIEW_OPTIONS_NEXT = "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_NEXT";
    public final static String ACTION_CUSTOM_VIEW_OPTIONS_LYRICS = "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_LYRICS";
    public final static String ACTION_CUSTOM_VIEW_OPTIONS_CANCEL = "com.android.peter.notificationdemo.ACTION_CUSTOM_VIEW_OPTIONS_CANCEL";

    public final static String ACTION_YES = "com.android.peter.notificationdemo.ACTION_YES";
    public final static String ACTION_NO = "com.android.peter.notificationdemo.ACTION_NO";
    public final static String ACTION_DELETE = "com.android.peter.notificationdemo.ACTION_DELETE";
    public final static String ACTION_REPLY = "com.android.peter.notificationdemo.ACTION_REPLY";
    public final static String REMOTE_INPUT_RESULT_KEY = "remote_input_content";

    public final static String EXTRA_OPTIONS = "options";
    public final static String MEDIA_STYLE_ACTION_DELETE = "action_delete";
    public final static String MEDIA_STYLE_ACTION_PLAY = "action_play";
    public final static String MEDIA_STYLE_ACTION_PAUSE = "action_pause";
    public final static String MEDIA_STYLE_ACTION_NEXT = "action_next";
    public final static String ACTION_ANSWER = "action_answer";
    public final static String ACTION_REJECT = "action_reject";


    private static volatile Notifications sInstance = null;

    private Notifications() {
    }

    public static Notifications getInstance() {
        if(sInstance == null) {
            synchronized (Notifications.class) {
                if(sInstance == null) {
                    sInstance = new Notifications();
                }
            }
        }

        return sInstance;
    }

    /**
     * 普通通知 8.0以下
     * @param context
     * @param nm
     */
    public void sendSimpleNotification(Context context, NotifyManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_SIMPLE);
        PendingIntent pi = PendingIntent.getService(context,0,intent,0);
        //创建删除通知时发送的广播
        Intent deleteIntent = new Intent(context,NotificationService.class);
        deleteIntent.setAction(ACTION_DELETE);
        PendingIntent deletePendingIntent = PendingIntent.getService(context,0,deleteIntent,0);
        //创建通知
        NotificationCompat.Builder nb = new NotificationCompat.Builder(context);

        //设置通知左侧的小图标
        nb.setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("Simple notification")
                //设置通知内容
                .setContentText("Demo for simple notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置通知右侧的大图标
                //  .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_notifiation_big))
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置删除通知时的响应事件
                .setDeleteIntent(deletePendingIntent);

        Notification notification = nb.build();
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.sound = uri;  //设置声音
        //发送通知
        nm.notifyNotify(notification);
    }
    /**
     * 普通通知8.0以上
     * @param context
     * @param nm
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendSimpleNotificationAndroidO(Context context, NotifyManager nm) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context,NotificationService.class);
        intent.setAction(ACTION_SIMPLE);
        PendingIntent pi = PendingIntent.getService(context,0,intent,0);
        //创建删除通知时发送的广播
        Intent deleteIntent = new Intent(context,NotificationService.class);
        deleteIntent.setAction(ACTION_DELETE);
        PendingIntent deletePendingIntent = PendingIntent.getService(context,0,deleteIntent,0);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context,Constants.CHANNEL_LOW);

                //设置通知左侧的小图标
                nb.setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("Simple notification")
                //设置通知内容
                .setContentText("Demo for simple notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置通知右侧的大图标
              //  .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_notifiation_big))
                //设置点击通知时的响应事件
                .setContentIntent(pi)
                //设置删除通知时的响应事件
                .setDeleteIntent(deletePendingIntent);

        Notification notification = nb.build();
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.sound = uri;  //设置声音
        //发送通知
        nm.notifyNotify(notification);
    }

    /**
     * 8.0以上发送进度通知
     * @param context
     * @param nm
     * @param progress
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendProgressViewNotificationAndroidO(Context context, NotificationManager nm, int progress) {
        //创建点击通知时发送的广播

        Intent intent = new Intent(context,NotificationService.class);
        intent.setAction(ACTION_PROGRESS);
        PendingIntent pi = PendingIntent.getService(context,0,intent,0);
        //创建通知
        Notification.Builder nb = new Notification.Builder(context,Constants.CHANNEL_PROGRESS)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("MyApplication Downloading...")
                //设置通知内容
                .setContentText(String.valueOf(progress) + "%")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置通知不可删除
                .setOngoing(true)
                //设置显示通知时间
                .setShowWhen(true)

                .setProgress(100,progress,false);
        if (progress==100){
            //设置点击通知时的响应事件
            nb.setContentIntent(pi);
        }
        //发送通知
        //进度通知id 一定要固定
      Notification notification = nb.build();
  //      notification.sound=null;
        nm.notify(Constants.PROGRESS_NOTIFY_ID,notification);
    }
    /**
     * 8.0以下发送进度通知
     * @param context
     * @param nm
     * @param progress
     */

    public void sendProgressViewNotification(Context context, NotificationManager nm, int progress) {
        //创建点击通知时发送的广播
        Intent intent = new Intent(context,NotificationService.class);
        intent.setAction(ACTION_PROGRESS);
        PendingIntent pi = PendingIntent.getService(context,0,intent,0);
        //创建通知
        //创建通知
        NotificationCompat.Builder nb = new NotificationCompat.Builder(context);
                //设置通知左侧的小图标
               nb.setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("MyApplication Downloading...")
              //设置点击通知后自动删除通知
               .setAutoCancel(true)
                //设置通知内容
                .setContentText(String.valueOf(progress) + "%")
                //设置通知不可删除
                .setOngoing(true)
                //设置显示通知时间
                .setShowWhen(true)
                .setProgress(100,progress,false);

        if (progress==100){
            //设置点击通知时的响应事件
            nb.setContentIntent(pi);
        }
        //发送通知
        //进度通知id 一定要固定
        Notification notification = nb.build();
        notification.sound=null;
        nm.notify(Constants.PROGRESS_NOTIFY_ID,nb.build());
    }
}
