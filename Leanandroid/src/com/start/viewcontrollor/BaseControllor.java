package com.start.viewcontrollor;

import android.app.Activity;
import android.view.View;

public abstract class BaseControllor {

	protected Activity activity;
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	protected void initMember(){
		
	}
	protected abstract void setListeners();
	protected void setAdapters(){
		
	}
	public BaseControllor(Activity activity) {
		this.activity = activity;
		initMember();
		setAdapters();
		setListeners();
	}
	public BaseControllor() {
	}
	protected View findViewById(int id){
		return activity.findViewById(id);
	}
}
