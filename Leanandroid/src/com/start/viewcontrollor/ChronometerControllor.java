package com.start.viewcontrollor;

import org.leanandroid.R;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;

import com.start.util.ListenerUtil;

public class ChronometerControllor extends BaseControllor{
	OnClickListener listener;
	Chronometer chronometer;
	public ChronometerControllor(Activity activity) {
		super(activity);
	}
	@Override
	protected void initMember() {
		chronometer = (Chronometer) activity.findViewById(R.id.generalcontrol_chronometer);
	}
	@Override
	protected void setListeners() {
		listener = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch (arg0.getId()) {
				case R.id.generalcontrol_start:
					//chronometer.setBase(SystemClock.elapsedRealtime());  
	                /** 
	                 * 设置现实的格式，你可以指定一个字符串，字符串中包含一个%s，然后chronometer 
	                 * 就会自动将mm:ss的那个时间放置到你的第一个%s的位置。 
	                 */  
	                //chronometer.setFormat("当前时间:%s");  
	                // 启动  
	                chronometer.start();
					break;
				case R.id.generalcontrol_clear_format:
					chronometer.setFormat(null);
					break;
				case R.id.generalcontrol_reset:
					chronometer.setBase(SystemClock.elapsedRealtime());
					break;
				case R.id.generalcontrol_set_format:
					chronometer.setFormat("Formatted time (%s)");
					break;
				case R.id.generalcontrol_stop:
					chronometer.stop();
					break;
				default:
					break;
				}
			}
		};
		ListenerUtil.setSameOnClickListener(
				new int[]{R.id.generalcontrol_clear_format,R.id.generalcontrol_reset,R.id.generalcontrol_set_format,
						R.id.generalcontrol_start,R.id.generalcontrol_stop}, activity, listener);
	}
	
}
