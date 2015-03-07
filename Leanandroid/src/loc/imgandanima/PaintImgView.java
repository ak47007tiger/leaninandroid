package loc.imgandanima;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.leanandroid.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class PaintImgView extends View {
	public PaintImgView(Context context) {
		super(context);
	}

	Bitmap bitmap;
	int hSum = 20;
	int vSum = 20;
	int sizeH;// 格子水平长
	int sizeV;// 格子垂直长
	int vertSum;// 顶点总数量
	int vertSumH;// 水平顶点数量
	int vertSumV;// 垂直顶点数量

	float[] array_origin;// 保存顶点横、纵坐标
	float[] array_cur;

	public PaintImgView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.jiker201300163343703);
		vertSumH = hSum + 1;
		vertSumV = vSum + 1;
		sizeH = bitmap.getWidth() / hSum;
		sizeV = bitmap.getHeight() / vSum;
		vertSum = (vertSumH) * (vertSumV);
		array_origin = new float[vertSum * 2];
		for (int i = 0; i < vertSum; i += 2) {
			array_origin[i] = i * sizeH;// 横坐标 = 顶点横向 * 格子水平长
			array_origin[i + 1] = i / vertSumH * sizeV;// 纵坐标 = 顶点纵向 * 顶点垂直长
		}
		array_cur = array_origin.clone();
		Timer timer;
		
		timer = new Timer();
		timer.schedule(timerTask, 1000, 1000);
	}
	TimerTask timerTask = new TimerTask() {
		Random random = new Random(System.currentTimeMillis());
		int x, y;
		@Override
		public void run() {
			x = random.nextInt(bitmap.getWidth());
			y = random.nextInt(bitmap.getHeight());
			for(int i = 0; i < vertSum; i+=2){
				array_cur[i] = array_origin[i] + x * y;
				array_cur[i + 1] = array_origin[i + 1] + x * y;
			}
			handler.sendEmptyMessage(0);
		}
	};
	Handler handler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			invalidate();
		};
	};
	public PaintImgView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmapMesh(bitmap, hSum, vSum, array_cur, 0, null, 0, null);
		canvas.drawBitmap(bitmap, 0, 400, null);
		Paint paint;
		paint = new Paint();
		paint.setColor(0x32dcf4);
		canvas.drawText("hello", 100, 100, paint);
	}
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		bitmap.recycle();
	}
}
