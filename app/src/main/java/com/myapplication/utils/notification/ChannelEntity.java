package com.myapplication.utils.notification;

import android.app.Notification;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ChannelEntity {

    private String channelId; // 渠道Id
    private String channelName; // 渠道名称
    private int importance; // 重要等级
    private String description; // 描述
    private boolean showBadge = true; // 是否显示icon角标


    private Uri sound = Settings.System.DEFAULT_NOTIFICATION_URI;//通知音
    private AudioAttributes mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;

    public ChannelEntity(@NonNull String channelId, @NonNull String channelName, @ImportanceType int importance) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.importance = importance;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getImportance() {
        return importance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowBadge() {
        return showBadge;
    }

    public void setShowBadge(boolean showBadge) {
        this.showBadge = showBadge;
    }

    public Uri getSound() {
        return sound;
    }

    public void setSound(Uri sound) {
        this.sound = sound;
    }

    public AudioAttributes getmAudioAttributes() {
        return mAudioAttributes;
    }

    public void setmAudioAttributes(AudioAttributes mAudioAttributes) {
        this.mAudioAttributes = mAudioAttributes;
    }

}
