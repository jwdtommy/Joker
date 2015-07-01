package com.jwd.jokers;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * 应用程序全局信息 存放全局变量
 */
public class App extends Application {

	private List<WeakReference<Activity>> mActivities = new LinkedList<WeakReference<Activity>>();

	private HashMap<String, Object> cache = new HashMap<String, Object>();
	private static App instance = null;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public WeakReference<Activity> addActivity(Activity activity) {
		synchronized (mActivities) {
			WeakReference<Activity> reference = new WeakReference<Activity>(activity);
			mActivities.add(reference);
			return reference;
		}
	}
    
	public int getCurrentRunningActivitySize() {

		return mActivities.size();
	}


	public void removeActivity(WeakReference<Activity> reference) {
		synchronized (mActivities) {
			mActivities.remove(reference);
		}
	}

	public void exit() {
		synchronized (mActivities) {
			for (WeakReference<Activity> activityRef : mActivities) {
				Activity activity = activityRef.get();
				if (activity != null) {
					activity.finish();
				}
			}
			mActivities.clear();
		}
	}

	public static App getInstance() {
		if (instance == null) {
			throw new NullPointerException("app not create should be terminated!");
		}
		return instance;
	}

	public Object get(String key) {
		return cache.get(key);
	}

	public Object remove(String key) {
		return cache.remove(key);
	}

	public void put(String key, Object value) {
		cache.put(key, value);
	}

	public void clearCache() {
		cache.clear();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		exit();
	}
}
