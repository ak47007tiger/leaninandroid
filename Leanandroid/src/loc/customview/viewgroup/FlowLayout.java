package loc.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {
	public FlowLayout(Context context) {
		super(context);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		int cCount = getChildCount();
		int pWidth = getWidth();
		int cl,ct,cr,cb;
		int curX = 0,curY = 0, curHeightMax = 0;
		/*
		 * 放一个，没满，再放一个，直到放下一个的时候满了，撤销，换一行
		 * 重复上面的事情，直到没有东西可以放
		 * 
		 */
		int i = 0;
		while (i < cCount) {
			View childView = getChildAt(i);
			int cWidth = childView.getMeasuredWidth();
			int cHieght = childView.getMeasuredHeight();
			curHeightMax = curHeightMax >= cHieght ? curHeightMax : cHieght; 
			curX += cWidth;
			if(curX <= pWidth){
				cl = curX - cWidth;
				ct = curY;
				cr = curX;
				cb = curY + cHieght;
				childView.layout(cl, ct, cr, cb);
				i++;
			}else{
				curX = 0;
				curY += curHeightMax;
				curHeightMax = 0;
				continue;
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/**
		 * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
		 */
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int width = 300;
		int height = 400;
		int cCount = getChildCount();
		for (int i = 0; i < cCount; i++) {
			View childView = getChildAt(i);
			int cWidth = childView.getMeasuredWidth();
			int cHieght = childView.getMeasuredHeight();
		}
		
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		switch(widthMode){
		case MeasureSpec.AT_MOST://至多
			break;
		case MeasureSpec.UNSPECIFIED://任意
			break;
		case MeasureSpec.EXACTLY://由父元素决定
			break;
		}
		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
				: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
				: height);
	}
	static final String tag = "FlowLayout";
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
		for (int i = 0; i < getChildCount(); i++) {
			getChildAt(i).onTouchEvent(ev);
		}
		
//		return super.onInterceptTouchEvent(ev);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		msg = "onTouchEvent";
		Log.i(tag, msg);
		boolean result = super.onTouchEvent(event);
		Log.i(tag, result?"true":"false");
		return true;
	}
}
