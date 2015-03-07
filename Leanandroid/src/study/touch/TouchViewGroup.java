package study.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class TouchViewGroup extends ViewGroup {
	public TouchViewGroup(Context context) {
		super(context);
	}

	public TouchViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TouchViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	static final String tag = "TouchViewGroup";
	String msg;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		msg = "dispatchTouchEvent";
		Log.i(tag, msg);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		msg = "onInterceptTouchEvent";
		Log.i(tag, msg);
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		msg = "onTouchEvent";
		Log.i(tag, msg);
		return super.onTouchEvent(event);
	}
}
