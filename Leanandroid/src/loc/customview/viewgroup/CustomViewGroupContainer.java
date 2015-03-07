package loc.customview.viewgroup;

import org.leanandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CustomViewGroupContainer extends Activity {

	Button button_big;
	Button button_small;
	ImageView imageView;
	FrameLayout imgContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*View v = LayoutInflater.from(this).inflate(R.layout.borderlayout, null);
		FrameLayout container = new FrameLayout(this);
		container.setLayoutParams(new FrameLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		container.addView(v);*/
		setContentView(R.layout.customviewgroupcontainer);
		View v = findViewById(R.id.btn5);
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("", "click");
			}
		});
		v.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("", "touch");
				return true;
			}
		});
		// View view =
		// LayoutInflater.from(this).inflate(R.layout.dragsizelayout, null);
		// setContentView(view);
		/*
		 * setContentView(R.layout.dragsizelayout); DragSizeLayout dl =
		 * (DragSizeLayout) findViewById(R.id.draglayout); btn = (Button)
		 * findViewById(R.id.btn); btn.setOnTouchListener(new OnTouchListener()
		 * { int curY;
		 * 
		 * @Override public boolean onTouch(View arg0, MotionEvent arg1) { int
		 * action = arg1.getAction(); int y = (int) arg1.getY(); switch (action)
		 * { case MotionEvent.ACTION_DOWN: curY = y; break; case
		 * MotionEvent.ACTION_MOVE: if (curY < y) { Log.d("", "down");
		 * moveDownSmoonth(); } else { Log.d("", "up"); moveUpSmoonth(); } curY
		 * = y; break; case MotionEvent.ACTION_UP: break; default: break; }
		 * return true; } });
		 */
		/*
		 * MoveImageView moveImage = new MoveImageView(this);
		 * moveImage.setLayoutParams(new ViewGroup.LayoutParams(
		 * LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		 * moveImage.setBackgroundResource(R.drawable.xiwer);
		 * dl.addView(moveImage);
		 */
	}

	Button btn;
	boolean goup = false;
	final int up = 0;
	final int down = 1;
	int speed = 10;
	Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case up:
				btn.setTop(btn.getTop() - speed);
				break;
			case down:
				btn.setTop(btn.getTop() + speed);
				break;
			}
		}
	};

	public void moveUpSmoonth() {
		handler.sendEmptyMessage(up);
	}

	public void moveDownSmoonth() {
		handler.sendEmptyMessage(down);
	}

/*	private void imgOp() {
		setContentView(R.layout.customviewgroupcontainer);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.xiwer);
		button_big = (Button) findViewById(R.id.big);
		button_small = (Button) findViewById(R.id.small);
		imageView = (ImageView) findViewById(R.id.img);
		imgContainer = (FrameLayout) findViewById(R.id.imgcontainer);
		button_big.setOnClickListener(onClickListener);
		button_small.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.big:
				// big();
				bigbylayout();
				break;
			case R.id.small:
				smallbylayout();
				// small();
				break;
			default:
				break;
			}
		}
	};
	Bitmap bmp;
	float scaleWidth = 1;
	float scaleHeight = 1;

	public void bigbylayout() {
		int width = (int) (imageView.getWidth() * 1.25);
		int height = (int) (imageView.getHeight() * 1.25);
		imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
	}

	public void smallbylayout() {
		int width = (int) (imageView.getWidth() * 0.8);
		int height = (int) (imageView.getHeight() * 0.8);
		imageView.setLayoutParams(new FrameLayout.LayoutParams(width, height));
	}

	 图片放大的method 
	private void big() {
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		 设置图片放大的比例 
		double scale = 1.25;
		 计算这次要放大的比例 
		scaleWidth = (float) (scaleWidth * scale);
		scaleHeight = (float) (scaleHeight * scale);
		 产生reSize后的Bitmap对象 
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight,
				matrix, true);
		imgContainer.removeView(findViewById(R.id.img));
		 产生新的ImageView，放入reSize的Bitmap对象，再放入Layout中 
		ImageView imageView = new ImageView(CustomViewGroupContainer.this);
		imageView.setId(R.id.img);
		imageView.setImageBitmap(resizeBmp);
		imgContainer.addView(imageView);
	}

	 图片缩小的method 
	private void small() {
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		 设置图片放大的比例 
		double scale = 0.75;
		 计算这次要放大的比例 
		scaleWidth = (float) (scaleWidth * scale);
		scaleHeight = (float) (scaleHeight * scale);
		 产生reSize后的Bitmap对象 
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight,
				matrix, true);
		imgContainer.removeView(findViewById(R.id.img));
		 产生新的ImageView，放入reSize的Bitmap对象，再放入Layout中 
		ImageView imageView = new ImageView(CustomViewGroupContainer.this);
		imageView.setId(R.id.img);
		imageView.setImageBitmap(resizeBmp);
		imgContainer.addView(imageView);
	}*/
}
