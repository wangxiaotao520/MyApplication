package com.myapplication.ui;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import static com.myapplication.utils.notification.Notifications.ACTION_ACTION;
import static com.myapplication.utils.notification.Notifications.ACTION_BIG_PICTURE_STYLE;
import static com.myapplication.utils.notification.Notifications.ACTION_BIG_TEXT_STYLE;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_HEADS_UP_VIEW;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_VIEW;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_VIEW_OPTIONS_CANCEL;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_VIEW_OPTIONS_LOVE;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_VIEW_OPTIONS_LYRICS;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_VIEW_OPTIONS_NEXT;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE;
import static com.myapplication.utils.notification.Notifications.ACTION_CUSTOM_VIEW_OPTIONS_PRE;
import static com.myapplication.utils.notification.Notifications.ACTION_DELETE;
import static com.myapplication.utils.notification.Notifications.ACTION_INBOX_STYLE;
import static com.myapplication.utils.notification.Notifications.ACTION_MEDIA_STYLE;
import static com.myapplication.utils.notification.Notifications.ACTION_MESSAGING_STYLE;
import static com.myapplication.utils.notification.Notifications.ACTION_NO;
import static com.myapplication.utils.notification.Notifications.ACTION_PROGRESS;
import static com.myapplication.utils.notification.Notifications.ACTION_REMOTE_INPUT;
import static com.myapplication.utils.notification.Notifications.ACTION_REPLY;
import static com.myapplication.utils.notification.Notifications.ACTION_SEND_PROGRESS_NOTIFICATION;
import static com.myapplication.utils.notification.Notifications.ACTION_SIMPLE;
import static com.myapplication.utils.notification.Notifications.ACTION_YES;

/**
 * Description:
 * created by wangxiaotao
 * 2020/4/29 0029 下午 4:10
 */
public class NotificationService extends Service {

    private Context mContext;
    private NotificationManager mNM;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null && intent.getAction() != null) {

            switch (intent.getAction()) {
                case ACTION_SIMPLE:
                    Toast.makeText(mContext,"简单消息点击",Toast.LENGTH_SHORT).show();
                    break;
                case ACTION_DELETE:
                    Toast.makeText(mContext,"简单消息删除",Toast.LENGTH_SHORT).show();
                    break;
                case ACTION_ACTION:
                    break;
                case ACTION_REMOTE_INPUT:
                    break;
                case ACTION_BIG_PICTURE_STYLE:
                    break;
                case ACTION_BIG_TEXT_STYLE:
                    break;
                case ACTION_INBOX_STYLE:
                    break;
                case ACTION_MEDIA_STYLE:

                    break;
                case ACTION_MESSAGING_STYLE:
                    break;
                case ACTION_YES:

                    break;
                case ACTION_NO:

                    break;

                case ACTION_REPLY:

                    break;
                case ACTION_PROGRESS:
                    Toast.makeText(mContext,"跳转到安装",Toast.LENGTH_SHORT).show();
                    break;
                case ACTION_SEND_PROGRESS_NOTIFICATION:
                    break;
                case ACTION_CUSTOM_HEADS_UP_VIEW:

                    break;
                case ACTION_CUSTOM_VIEW:
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_LOVE:

                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_PRE:

                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE:

                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_NEXT:

                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_LYRICS:
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_CANCEL:

                    break;
                default:
                    //do nothing
            }
        }

        return START_STICKY;
    }
}
