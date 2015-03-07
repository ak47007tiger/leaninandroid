package com.start.util;

import android.app.Activity;
import android.util.DisplayMetrics;

public class WindowUtil {
	private WindowUtil() {
	}
	private static WindowUtil windowUtil;
	public static WindowUtil sharedWindowUtil(){
		if(null == windowUtil){
			windowUtil = new WindowUtil();
		}
		return windowUtil;
	}
	/**
	 * 大小 密度 密度比
	 * @param activity
	 * @return
	 */
	public DisplayMetrics getWindowDisplayMetrics(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
		/*screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		float density = dm.density;
		int densityDpi = dm.densityDpi;// = length_px / length_dp*/
	}

	public int getPxByDp(int dp, Activity activity) {
		DisplayMetrics dm = getWindowDisplayMetrics(activity);
		return (int) (dp * dm.density);
	}
	public int getDpByPx(int px, Activity activity){
		return px / getWindowDisplayMetrics(activity).densityDpi;
	}
}
