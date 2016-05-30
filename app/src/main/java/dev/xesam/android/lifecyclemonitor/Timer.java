package dev.xesam.android.lifecyclemonitor;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

/**
 * Created by xesamguo@gmail.com on 16-5-30.
 */
public abstract class Timer {

    public abstract void onTimerTick(long millisUntilFinished);

    CountDownTimer countDownTimer = new CountDownTimer(100 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            onTimerTick(millisUntilFinished);
            Log.d("Timer onTick", millisUntilFinished + "");
        }

        @Override
        public void onFinish() {
            Log.d("Timer onFinish", "onFinish");
        }
    };

    public void attach(Activity activity) {
        LifecycleMonitor.instance().register(activity, new LifecycleMonitor.ComponentHook() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.e("Timer", "onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.e("Timer", "onActivityStarted");
                countDownTimer.start();
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.e("Timer", "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.e("Timer", "onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.e("Timer", "onActivityStopped");
                countDownTimer.cancel();
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.e("Timer", "onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.e("Timer", "onActivityDestroyed");
            }
        });
    }
}
