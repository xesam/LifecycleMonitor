package dev.xesam.android.lifecyclemonitor;

import android.app.Application;

/**
 * Created by xesamguo@gmail.com on 16-5-30.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LifecycleMonitor.instance().attachApplication(this);
    }
}
