package loc.paintview;

import org.leanandroid.R;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestPaintBaseView extends View{

	public TestPaintBaseView(Context context) {
		super(context);
	}
	public TestPaintBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override 
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)    
	{  
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int height,width;
	    height = View.MeasureSpec.getSize(heightMeasureSpec);   
	    width = View.MeasureSpec.getSize(widthMeasureSpec);   
	    //这里面是原始的大小，需要重新计算可以修改本行  
	    setMeasuredDimension(600,600);  
	  //dosomething  
	}  
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();  
        paint.setColor(Color.BLUE);  
        //设置字体大小  
        paint.setTextSize(100);  
          
        //让画出的图形是空心的  
        paint.setStyle(Paint.Style.STROKE);  
        //设置画出的线的 粗细程度  
        paint.setStrokeWidth(5);  
        //画出一根线  
        canvas.drawLine(0, 0, 200, 200, paint);  
          
        //画矩形  
        canvas.drawRect(200, 500, 300, 300, paint);  
          
        //画圆  
        canvas.drawCircle(200, 200, 100, paint);  
        //画出字符串 drawText(String text, float x, float y, Paint paint)   
        // y 是 基准线 ，不是 字符串的 底部  
        canvas.drawText("apple", 60, 60, paint);  
        canvas.drawLine(0, 60, 500, 60, paint);  
          
        //绘制图片  
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 150, 150, paint);  
          
        super.onDraw(canvas);
	}
}
