package org.leanandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service{
	TestServiceBinder binder = new TestServiceBinder();
	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(tag, "onBind");
		return binder;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(tag, "onCreate");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(tag, "onStartCommand");
		return START_NOT_STICKY;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(tag, "onDestroy");
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(tag, "onUnbind");
		return super.onUnbind(intent);
	}
	static final String tag = "TestService";
	public static final String action = "org.leanandroid.service.TestService";
	public class TestServiceBinder extends Binder{
		public void printfName(){
			Log.i(tag, getName());
		}
	}
	public String getName(){
		return TestService.class.getName() + System.currentTimeMillis();
	}
}
