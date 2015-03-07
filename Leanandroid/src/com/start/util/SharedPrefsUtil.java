package com.start.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences存储数据方式工具类
 */
public class SharedPrefsUtil {
	public final static String SETTING = "Setting";
	public static void putValue(Context context,String preferenceName, int mode,String key, int value) {
		 Editor sp =  context.getSharedPreferences(preferenceName, mode).edit();
		 sp.putInt(key, value);
		 sp.commit();
	}
	public static void putValue(Context context,String preferenceName, int mode,String key, boolean value) {
		 Editor sp =  context.getSharedPreferences(preferenceName, mode).edit();
		 sp.putBoolean(key, value);
		 sp.commit();
	}
	public static void putValue(Context context,String preferenceName,int mode,String key, String value) {
		 Editor sp =  context.getSharedPreferences(preferenceName, mode).edit();
		 sp.putString(key, value);
		 sp.commit();
	}
	public static int getValue(Context context,String preferenceName,int mode,String key, int defValue) {
		SharedPreferences sp =  context.getSharedPreferences(preferenceName, mode);
		int value = sp.getInt(key, defValue);
		return value;
	}
	public static boolean getValue(Context context,String preferenceName,int mode,String key, boolean defValue) {
		SharedPreferences sp =  context.getSharedPreferences(preferenceName, mode);
		boolean value = sp.getBoolean(key, defValue);
		return value;
	}
	public static String getValue(Context context,String preferenceName,int mode,String key, String defValue) {
		SharedPreferences sp =  context.getSharedPreferences(preferenceName, mode);
		String value = sp.getString(key, defValue);
		return value;
	}
}
