package dev.xesam.android.lifecyclemonitor;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xesamguo@gmail.com on 16-5-30.
 */
public class LifecycleMonitor {
    public interface ComponentHook {
        void onActivityCreated(Activity activity, Bundle savedInstanceState);

        void onActivityStarted(Activity activity);

        void onActivityResumed(Activity activity);

        void onActivityPaused(Activity activity);

        void onActivityStopped(Activity activity);

        void onActivitySaveInstanceState(Activity activity, Bundle outState);

        void onActivityDestroyed(Activity activity);
    }

    static LifecycleMonitor sLifecycleMonitor;

    public static LifecycleMonitor instance() {
        if (sLifecycleMonitor == null) {
            sLifecycleMonitor = new LifecycleMonitor();
        }
        return sLifecycleMonitor;
    }

    private SparseArray<List<ComponentHook>> componentHooks = new SparseArray<>();

    public Application.ActivityLifecycleCallbacks appHook = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
            if (hooks == null) {
                componentHooks.put(activity.hashCode(), new ArrayList<ComponentHook>());
            } else {
                for (ComponentHook componentHook : hooks) {
                    componentHook.onActivityCreated(activity, savedInstanceState);
                }
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
            if (hooks != null) {
                for (ComponentHook componentHook : hooks) {
                    componentHook.onActivityStarted(activity);
                }
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
            if (hooks != null) {
                for (ComponentHook componentHook : hooks) {
                    componentHook.onActivityResumed(activity);
                }
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
            if (hooks != null) {
                for (ComponentHook componentHook : hooks) {
                    componentHook.onActivityPaused(activity);
                }
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
            if (hooks != null) {
                for (ComponentHook componentHook : hooks) {
                    componentHook.onActivityStopped(activity);
                }
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
            if (hooks != null) {
                for (ComponentHook componentHook : hooks) {
                    componentHook.onActivitySaveInstanceState(activity, outState);
                }
            }
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
            if (hooks != null) {
                for (ComponentHook componentHook : hooks) {
                    componentHook.onActivityDestroyed(activity);
                }
            }
            componentHooks.remove(activity.hashCode());
        }
    };

    public void attachApplication(Application application) {
        application.registerActivityLifecycleCallbacks(appHook);
    }

    public void register(Activity activity, ComponentHook componentHook) {
        List<ComponentHook> hooks = componentHooks.get(activity.hashCode());
        if (hooks == null) {
            Log.e("LifecycleMonitor", "did you called attachApplication(Application)?");
        } else {
            hooks.add(componentHook);
        }
    }
}
