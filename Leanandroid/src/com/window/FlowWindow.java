package com.window;

import org.leanandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class FlowWindow extends Activity {

	Button open = null;
	Button close = null;
	WindowManager wm;
	WindowManager.LayoutParams wmParams;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flowwindow);
		open = (Button) findViewById(R.id.open);
		close = (Button) findViewById(R.id.close);
		wm = (WindowManager) getApplicationContext().getSystemService("window");
		wmParams = new WindowManager.LayoutParams();
		/**
		 * 以下都是WindowManager.LayoutParams的相关属性 具体用途请参考SDK文档
		 */
		wmParams.type = 2002; // 这里是关键，你也可以试试2003
		wmParams.format = 1;
		/**
		 * 这里的flags也很关键 代码实际是wmParams.flags |= FLAG_NOT_FOCUSABLE;
		 * 40的由来是wmParams的默认属性（32）+ FLAG_NOT_FOCUSABLE（8）
		 */
		wmParams.flags = 40;
		wmParams.width = 140;
		wmParams.height = 40;
		wmParams.x = 100;
		wmParams.y = 100;
		// 调整悬浮窗显示的停靠位置为左侧置顶
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;

		open.setOnClickListener(onClickListener);
		close.setOnClickListener(onClickListener);
		v = LayoutInflater.from(FlowWindow.this).inflate(
				R.layout.floatwindowlayout, null);
		v.findViewById(R.id.showmsg).setOnClickListener(onClickListener);
		v.findViewById(R.id.showmsg).setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Toast.makeText(FlowWindow.this, "key back", 1000).show();
				return false;
			}
		});
		v.setOnTouchListener(onTouchListener);
	}

	View v;
	float touch_x;
	float touch_y;
	OnTouchListener onTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			int action = arg1.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				touch_x = arg1.getX();
				touch_y = arg1.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				int x_sub = (int) (arg1.getRawX() - touch_x);
				int y_sub = (int) (arg1.getRawY() - touch_y);
				wmParams.x = x_sub;
				wmParams.y = y_sub;
				wm.updateViewLayout(v, wmParams);
				break;
			}
			return false;
		}
	};
	Handler handler = new Handler();
	OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			if (arg0 == open) {
				wm.addView(v, wmParams); // 创建View
				Log.d("touch", wmParams.x + "," + wmParams.y);
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Log.d("touch", wmParams.x + "," + wmParams.y);
					}
				}, 1000);
			} else if (arg0 == close) {
				wm.removeView(v);
			} else if (arg0.getId() == R.id.showmsg) {
				Toast.makeText(FlowWindow.this, "msg", 1000).show();
			}
		}
	};
}
