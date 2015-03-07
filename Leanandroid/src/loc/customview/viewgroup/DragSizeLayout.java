package loc.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class DragSizeLayout extends LinearLayout {
	String TAG = DragSizeLayout.class.getSimpleName();

	public DragSizeLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "dispatchTouchEvent ACTION_UP");
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{

		int action = event.getAction();

		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "onTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "onTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "onTouchEvent ACTION_UP");
			break;

		default:
			break;
		}

		return super.onTouchEvent(event);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		
		int action = ev.getAction();
		switch (action)
		{
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "onInterceptTouchEvent ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "onInterceptTouchEvent ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "onInterceptTouchEvent ACTION_UP");
			break;

		default:
			break;
		}
		
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public void requestDisallowInterceptTouchEvent(boolean disallowIntercept)
	{
		Log.e(TAG, "requestDisallowInterceptTouchEvent ");
		super.requestDisallowInterceptTouchEvent(disallowIntercept);
	}

}
