package com.circlemenu;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class CircleMenu1 extends ViewGroup {

	public CircleMenu1(Context context) {
		super(context);
	}

	public CircleMenu1(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleMenu1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		int cCount = getChildCount(), i = 0;
		int r = 100;
		double totalLength = 2 * Math.PI * r;
		double length = totalLength / cCount;
		Log.d("layout", totalLength + "/" + length);

		for (i = 0; i < cCount; i++) {
			CirPoint cirPoint = new CirPoint(r, length * i);
			cirPoints.add(cirPoint);
			View child = getChildAt(i);
			int[] layout = cirPoint.getLayout(50);
			child.layout(layout[0], layout[1], layout[2], layout[3]);
		}
	}

	List<CirPoint> cirPoints = new ArrayList<CirPoint>();
	int xBase;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int width = 400;
		int height = 400;
		/*
		 * DisplayMetrics dm = new DisplayMetrics(); ((Activity)
		 * getContext()).getWindowManager().getDefaultDisplay() .getMetrics(dm);
		 */
		// width = dm.widthPixels;
		height = width;
		xBase = width / 6;

		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
				: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
				: height);
	}

	int yBefore = -1;
	int xBefore = -1;

	boolean hasMoveInChild = false;
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			onTouchEvent(ev);
			hasMoveInChild = false;
			break;
		case MotionEvent.ACTION_MOVE:
			onTouchEvent(ev);
			hasMoveInChild = true;
			break;
		case MotionEvent.ACTION_UP:
			if(hasMoveInChild){
				onTouchEvent(ev);
			}
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int y = (int) event.getY();
		int x = (int) event.getX();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			yBefore = y;
			xBefore = x;
			break;
		case MotionEvent.ACTION_MOVE:
			int i,
			cCount = getChildCount();
			for (i = 0; i < cCount; i++) {
				View child = getChildAt(i);
				CirPoint cirPoint = cirPoints.get(i);
				// left
				if (x < getWidth() / 2) {
					casekey = 0;
					cirPoint.changeToNewPoint(cirPoint.length + (y - yBefore));// 弧度变小
				} else {
					casekey = 1;
					cirPoint.changeToNewPoint(cirPoint.length + (yBefore - y));// 弧度变大
				}
				// top
				if (y < getWidth() / 2) {
					casekey = 1;
					cirPoint.changeToNewPoint(cirPoint.length + (xBefore - x));// 弧度变大
				} else {
					casekey = 0;
					cirPoint.changeToNewPoint(cirPoint.length + (x - xBefore));// 弧度变小
				}
				int[] layout = cirPoint.getLayout(50);
				child.layout(layout[0], layout[1], layout[2], layout[3]);
			}
			yBefore = y;
			xBefore = x;
			break;
		case MotionEvent.ACTION_UP:
			Thread thread = new Thread(new Keep());
			thread.start();
			break;
		}
		return true;
	}

	Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			int i, cCount = getChildCount();
			for (i = 0; i < cCount; i++) {
				View child = getChildAt(i);
				CirPoint cirPoint = cirPoints.get(i);
				if (0 == casekey) {
					cirPoint.changeToNewPoint(cirPoint.length - speed);
				} else {
					cirPoint.changeToNewPoint(cirPoint.length + speed);
				}
				int[] layout = cirPoint.getLayout(50);
				child.layout(layout[0], layout[1], layout[2], layout[3]);
			}
		}
	};
	int speed = 5;
	int casekey;

	class Keep implements Runnable {
		long time = 2000;

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start < time) {
				handler.sendEmptyMessage(1);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
