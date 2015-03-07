package com.gesturepsw;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class GesturePanel extends ViewGroup {

	Point[] points = new Point[9];

	LinkedList<Point> line_path = new LinkedList<Point>();

	public GesturePanel(Context context) {
		super(context);
	}

	public GesturePanel(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GesturePanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	int sizeOfCellContent = 60;

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		int cCount = getChildCount(), i = 0;
		int l = 0, t = 0, r = 0, b = 0;
		int cx, cy, childWidth, childHeight;
		for (i = 0; i < cCount; i++) {
			View v = getChildAt(i);
			childWidth = v.getMeasuredWidth();
			childHeight = v.getMeasuredHeight();
			childWidth = sizeOfCellContent;
			childHeight = sizeOfCellContent;
			cx = points[i].x;
			cy = points[i].y;
			l = cx - childWidth / 2;
			r = cx + childWidth / 2;
			t = cy - childHeight / 2;
			b = cy + childHeight / 2;
			v.layout(l, t, r, b);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawLines(canvas);
	}

	private void drawLines(Canvas canvas) {
		if (line_path.size() > 0) {
			Paint paint = new Paint();

			paint.setAntiAlias(true);

			paint.setColor(Color.BLUE);

			paint.setStyle(Paint.Style.STROKE);// 设置为空心

			paint.setStrokeWidth(3);
			Path path = new Path();
			int i = 0;
			Point point;
			point = line_path.get(i);
			i++;
			path.moveTo(point.x, point.y);
			for (; i < line_path.size(); i++) {
				point = line_path.get(i);
				path.lineTo(point.x, point.y);
			}
			canvas.drawPath(path, paint);
		}
	}

	int index_before;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int y = (int) event.getY();
		int x = (int) event.getX();
		int index;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			line_path.clear();
			index = getCellIndex(x, y);
			if(-1 != index){
				line_path.add(points[index]);
				index_before = index;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			index = getCellIndex(x, y);
			if (index != index_before && -1 != index) {
				line_path.add(points[index]);
				index_before = index;
				invalidate();
			}
			break;
		case MotionEvent.ACTION_UP:
			invalidate();
			break;
		}
		return true;
	}

	private int getCellIndex(int x, int y) {
		for (int i = 0; i < points.length; i++) {
			Point point = points[i];
			/*
			 * Rect rect = new Rect(point.x - halfCellWidth, point.y -
			 * halfCellHeight, point.x + halfCellWidth, point.y +
			 * halfCellHeight);
			 */
			Rect rect = new Rect(point.x - sizeOfCellContent, point.y
					- sizeOfCellContent, point.x + sizeOfCellContent, point.y
					+ sizeOfCellContent);
			if (rect.contains(x, y)) {
				return i;
			}
		}
		return -1;
	}

	int cellWidth;
	int cellHeight;
	int halfCellWidth;
	int halfCellHeight;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int width = 540;
		int height = 540;
		/*
		 * DisplayMetrics dm = new DisplayMetrics(); ((Activity)
		 * getContext()).getWindowManager().getDefaultDisplay() .getMetrics(dm);
		 * 
		 * width = dm.widthPixels; height = width;
		 */

		halfCellWidth = sizeWidth / 6;
		halfCellHeight = sizeHeight / 6;
		cellWidth = sizeWidth / 3;
		cellHeight = sizeHeight / 3;
		for (int i = 0; i < 9; i++) {
			points[i] = new Point();
		}
		points[0].x = halfCellWidth;
		points[1].x = 3 * halfCellWidth;
		points[2].x = 5 * halfCellWidth;
		points[3].x = halfCellWidth;
		points[4].x = 3 * halfCellWidth;
		points[5].x = 5 * halfCellWidth;
		points[6].x = halfCellWidth;
		points[7].x = 3 * halfCellWidth;
		points[8].x = 5 * halfCellWidth;

		points[0].y = halfCellHeight;
		points[3].y = 3 * halfCellHeight;
		points[6].y = 5 * halfCellHeight;
		points[1].y = halfCellHeight;
		points[4].y = 3 * halfCellHeight;
		points[7].y = 5 * halfCellHeight;
		points[2].y = halfCellHeight;
		points[5].y = 3 * halfCellHeight;
		points[8].y = 5 * halfCellHeight;

		measureChildren(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
				: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
				: height);
	}
}
