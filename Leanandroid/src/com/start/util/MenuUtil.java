package com.start.util;

import org.leanandroid.IndexMenuActivity;

import android.app.Activity;
import android.content.Intent;

public class MenuUtil {

	public static void goToActivity(Activity activityObj,Class<?> activityClass){
		Intent intent = new Intent(activityObj, activityClass);
		activityObj.startActivity(intent);
		activityObj.finish();
	}
	public static void goToMain(Activity activityObj){
		goToActivity(activityObj, IndexMenuActivity.class);
	}
}
