package com.circlemenu;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class CircleMenu extends ViewGroup {

	public CircleMenu(Context context) {
		super(context);
	}

	public CircleMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
//		int cl, ct, cr, cb;
		int cCount = getChildCount(), i = 0;

		for (i = 0; i < cCount; i++) {
			View child = getChildAt(i);
			int[] layout = layouts.get(i);
			child.layout(layout[0], layout[1], layout[2], layout[3]);
		}
	}

	int xBase;

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int width = 400;
		int height = 400;
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) getContext()).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
//		width = dm.widthPixels;
		height = width;
		xBase = width / 6;
		Point menu1Point = new Point(xBase, 0);
		Point menu2Point = new Point(3 * xBase, 0);
		Point menu3Point = new Point(4 * xBase, 2 * xBase);
		Point menu4Point = new Point(3 * xBase, 4 * xBase);
		Point menu5Point = new Point(xBase, 4 * xBase);
		Point menu6Point = new Point(0, 2 * xBase);
		layouts.add(toLayout(menu1Point));
		layouts.add(toLayout(menu2Point));
		layouts.add(toLayout(menu3Point));
		layouts.add(toLayout(menu4Point));
		layouts.add(toLayout(menu5Point));
		layouts.add(toLayout(menu6Point));

		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
				: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
				: height);
	}

	int[] toLayout(Point point) {
		int[] layout = new int[4];
		layout[0] = point.x;
		layout[1] = point.y;
		layout[2] = point.x + xBase;
		layout[3] = point.y + xBase;
		return layout;
	}
	class Point{
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point(int x, int y, int r){
			this.x = x;
			this.y = y;
			this.r = r;
		}
		int x , y , r;
		double length;
		public void changeToNewPoint(double length){
			
		}
	}
	List<int[]> layouts = new ArrayList<int[]>();
}
