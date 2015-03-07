package loc.customview.viewgroup;

import org.leanandroid.R;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UpDownLinear extends LinearLayout{
	public UpDownLinear(Context context) {
		super(context);
	}
	public UpDownLinear(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	float rateBigger = 1.1f;
	float rateSmalller = 0.9f;
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		this.setOrientation(LinearLayout.VERTICAL);
		up_view = (ImageView) inflate(getContext(), R.layout.updownlinearout_up, null);
		down_view = inflate(getContext(), R.layout.updownlinearout_down, null);
		addView(up_view);
		addView(down_view);
		down_view.setOnDragListener(new OnDragListener() {
			@Override
			public boolean onDrag(View arg0, DragEvent arg1) {
				return false;
			}
		});
		bz = new Bz();
		up_view.setPadding(0, 0, 0, 0);
//		action_.start();
	}
	Bz bz;
	Thread action_ = new Thread(new Runnable(){
		@Override
		public void run() {
			while(true){
				if(up == derict){
					bz.snedUp();
				}
				if(down == derict){
					bz.sendDown();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	ImageView up_view;
	View down_view;
	int curY;
	int minH = 100;
	int maxH = 300;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int y = (int) event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			curY = y;
			isdown = true;
			synchronized (bz) {
				bz.notifyAll();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			int height = (int) (up_view.getHeight() +  y - curY);
			up_view.setLayoutParams(new LinearLayout.LayoutParams(height, height));
			/*if (curY < y) {
				Log.d("", "down");
				derict = down;
				int height = (int) (up_view.getHeight() +  y - curY);
				up_view.setLayoutParams(new LinearLayout.LayoutParams(height, height));
			} 
			else if (curY > y) {
				derict = up;
				int newH = (up_view.getHeight() -  (curY - y));
				up_view.setLayoutParams(new LinearLayout.LayoutParams(newH, newH));
//				bz.smallbylayout();
			}
			else{
				derict = 2;
			}*/
			curY = y;
			break;
		case MotionEvent.ACTION_UP:
			isdown = false;
			break;
		default:
			break;
		}
		return true;
	}
	int derict = 2;
	boolean isdown = false;
	final int up = 0;
	final int down = 1;
	Handler handler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case up:
				bz.smallbylayout();
				break;
			case down:
				bz.bigbylayout();
				break;
			}
		}
	};
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
	class Bz{
		public synchronized void snedUp(){
			while(!isdown){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			handler.sendEmptyMessage(up);
		}
		public synchronized void sendDown(){
			while(!isdown){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			handler.sendEmptyMessage(down);
		}
		public void bigbylayout() {
			int height = (int) (up_view.getHeight() * rateBigger);
			if(maxH <= height){
				return;
			}
			int width = (int) (up_view.getWidth() * rateBigger);
			up_view.setLayoutParams(new LinearLayout.LayoutParams(width, height));
			up_view.setLeft(30);
		}
		public void smallbylayout() {
			int height = (int) (up_view.getHeight() * rateSmalller);
			if(height <= minH){
				return;
			}
			int width = (int) (up_view.getWidth() * rateSmalller);
			up_view.setLayoutParams(new LinearLayout.LayoutParams(width, height));
			up_view.setLeft(30);
		}
	}
}
