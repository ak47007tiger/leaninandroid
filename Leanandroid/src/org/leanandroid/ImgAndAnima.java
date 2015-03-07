package org.leanandroid;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;

public class ImgAndAnima extends Activity{
	TranslateAnimation translateAnimation;
	TranslateAnimation translateAnimation0;
	View txt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imgandanima);
		translateAnimation = new TranslateAnimation(0.1f, 100.0f,0.1f,100.0f);  
		translateAnimation0 = new TranslateAnimation(100.0f, 0.1f,100.0f,0.1f);  
		translateAnimation0.setDuration(1000);
        //设置动画时间  
        translateAnimation.setDuration(1000);  
        txt = findViewById(R.id.moveabletext);  
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		}, 1000, 2000);
        new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(1);
			}
		}, 2000, 2000);
	}
	Handler handler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				txt.startAnimation(translateAnimation);
				break;
			case 1:
				txt.startAnimation(translateAnimation0);
				break;
			}
		}
	};
}
