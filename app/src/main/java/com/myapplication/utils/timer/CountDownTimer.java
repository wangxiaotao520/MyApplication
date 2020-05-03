package com.myapplication.utils.timer;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Schedule a countdown until a time in the future, with
 * regular notifications on intervals along the way.
 *
 * Example of showing a 30 second countdown in a text field:
 *
 *
 * new CountDownTimer(30000, 1000) {
 *
 *   public void onTick(long millisUntilFinished) {
 *     mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
 *   }
 *
 *   public void onFinish() {
 *     mTextField.setText("done!");
 *   }
 * }.start();
 *
 *
 * The calls to {@link #onTick(long)} are synchronized to this object so that
 * one call to {@link #onTick(long)} won't ever occur before the previous
 * callback is complete. This is only relevant when the implementation of
 * {@link #onTick(long)} takes an amount of time to execute that is significant
 * compared to the countdown interval.
 *
 * 倒计时的类
 */
public abstract class CountDownTimer {
    private boolean isStarted;

    /**
     * Millis since epoch when alarm should stop.
     */
    private long mMillisInFuture;

    /**
     * The interval in millis that the user receives callbacks
     */
    private final long mCountdownInterval;



    private long mStopTimeInFuture;

    public void setParam(String param) {
        this.param = param;
    }

    private String param;

    /**

     * @param millisInFuture The number of millis in the future from the call
     *  to {@link #start()} until the countdown is done and {@link #onFinish(String)}
     *  is called.
     * @param countDownInterval The interval along the way to receive
     *  {@link #onTick(long)} callbacks.
     */
    public CountDownTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }

    /**
     * Cancel the countdown.
     */
    public final void cancel() {
        mHandler.removeMessages(MSG);
        setStarted(false);
    }

    /**
     * Start the countdown.
     */
    public synchronized final CountDownTimer start() {
        if (mMillisInFuture <= 0) {
            onFinish(param);
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        setStarted(true);
        return this;
    }


    /**
     * Callback fired on regular interval.
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish(String redpackage_id);

    public void setmStopTimeInFuture(long mMillisInFuture) {
        this.mMillisInFuture = mMillisInFuture;
    }

    public long getmStopTimeInFuture() {
        return mStopTimeInFuture;
    }

    private static final int MSG = 1;


    // handles counting down
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CountDownTimer.this) {
                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

                if (millisLeft <= 0) {
                    onFinish(param);
                } else if (millisLeft < mCountdownInterval) {
                    // no tick, just delay until done
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);

                    // take into account user's onTick taking time to execute
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) delay += mCountdownInterval;

                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        }
    };

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}