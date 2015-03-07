package com.start.util;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class ListenerUtil {

	public static void setSameOnClickListener(View[] arrView, OnClickListener listener){
		for(View view : arrView){
			view.setOnClickListener(listener);
		}
	}
	public static void setSameOnClickListener(int[] ids, Activity activity, OnClickListener listener){
		for (int id : ids){
			activity.findViewById(id).setOnClickListener(listener);
		}
	}
}
