package loc.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;

public class MoveImageView extends ImageView {
	public MoveImageView(Context context) {
		super(context);
	}

	public MoveImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MoveImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction();
		switch (eventaction) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE: {
			Log.d("", "move");
			// 在这里设置的话效果就是一顿神闪......
			// int X = (int) event.getX();
			// int Y = (int) event.getY();
			// if( X > 0 && Y > 0)
			// this.layout(X, Y,X + this.getWidth(),Y + this.getHeight());
			break;

		}
		case MotionEvent.ACTION_UP: {
			int x = (int) event.getX();
			int y = (int) event.getY();
			if (x > 0 && y > 0) {
				this.layout(x, y, x + this.getWidth(), y + this.getHeight());
			}
			break;
		}
		}
		this.invalidate();
		return true;
	}
}