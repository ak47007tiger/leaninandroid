package loc.paintview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class PaintPathView extends View{

	public PaintPathView(Context context) {
		super(context);
	}
	public PaintPathView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(600, 600);
	}
	/**  
     * Paint类介绍  
     *   
     * Paint即画笔，在绘图过程中起到了极其重要的作用，画笔主要保存了颜色，  
     * 样式等绘制信息，指定了如何绘制文本和图形，画笔对象有很多设置方法，  
     * 大体上可以分为两类，一类与图形绘制相关，一类与文本绘制相关。         
     *   
     * 1.图形绘制  
     * setARGB(int a,int r,int g,int b);  
     * 设置绘制的颜色，a代表透明度，r，g，b代表颜色值。  
     *   
     * setAlpha(int a);  
     * 设置绘制图形的透明度。  
     *   
     * setColor(int color);  
     * 设置绘制的颜色，使用颜色值来表示，该颜色值包括透明度和RGB颜色。  
     *   
    * setAntiAlias(boolean aa);  
     * 设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。  
     *   
     * setDither(boolean dither);  
     * 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰  
     *   
     * setFilterBitmap(boolean filter);  
     * 如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，加快显示  
     * 速度，本设置项依赖于dither和xfermode的设置  
     *   
     * setMaskFilter(MaskFilter maskfilter);  
     * 设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等       *   
     * setColorFilter(ColorFilter colorfilter);  
     * 设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果  
     *   
     * setPathEffect(PathEffect effect);  
     * 设置绘制路径的效果，如点画线等  
     *   
     * setShader(Shader shader);  
     * 设置图像效果，使用Shader可以绘制出各种渐变效果  
     *  
     * setShadowLayer(float radius ,float dx,float dy,int color);  
     * 在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色  
     *   
     * setStyle(Paint.Style style);  
     * 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE  
     *   
     * setStrokeCap(Paint.Cap cap);  
     * 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式  
     * Cap.ROUND,或方形样式Cap.SQUARE  
     *   
     * setSrokeJoin(Paint.Join join);  
     * 设置绘制时各图形的结合方式，如平滑效果等  
     *   
     * setStrokeWidth(float width);  
     * 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度  
     *   
     * setXfermode(Xfermode xfermode);  
     * 设置图形重叠时的处理方式，如合并，取交集或并集，经常用来制作橡皮的擦除效果  
     *   
     * 2.文本绘制  
     * setFakeBoldText(boolean fakeBoldText);  
     * 模拟实现粗体文字，设置在小字体上效果会非常差  
     *   
     * setSubpixelText(boolean subpixelText);  
     * 设置该项为true，将有助于文本在LCD屏幕上的显示效果  
     *   
     * setTextAlign(Paint.Align align);  
     * 设置绘制文字的对齐方向  
     *   
   * setTextScaleX(float scaleX);  
    * 设置绘制文字x轴的缩放比例，可以实现文字的拉伸的效果  
     *   
     * setTextSize(float textSize);  
     * 设置绘制文字的字号大小  
     *   
     * setTextSkewX(float skewX);  
     * 设置斜体文字，skewX为倾斜弧度  
     *   
     * setTypeface(Typeface typeface);  
     * 设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等  
     *   
     * setUnderlineText(boolean underlineText);  
     * 设置带有下划线的文字效果  
     *   
     * setStrikeThruText(boolean strikeThruText);  
     * 设置带有删除线的效果  
     *   
     */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();

        paint.setAntiAlias(true);

        paint.setColor(Color.RED);

        paint.setStyle(Paint.Style.STROKE);//设置为空心

        paint.setStrokeWidth(3);

        canvas.drawCircle(40, 40, 30, paint);

        canvas.drawRect(10, 90, 70, 150, paint);

        canvas.drawRect(10, 170, 70, 200, paint);

        canvas.drawOval(new RectF(10, 220, 70, 250), paint);

        Path path = new Path();//三角形

        path.moveTo(10, 330);

        path.lineTo(70, 330);

        path.lineTo(40, 270);

        path.close();

        canvas.drawPath(path, paint);

        Path path1 = new Path();//梯形

        path1.moveTo(10, 410);//绘画基点

        path1.lineTo(70, 410);

        path1.lineTo(55, 350);

        path1.lineTo(25, 350);

        path1.close();//把开始的点和最后的点连接在一起，构成一个封闭图形
        /*
         * 最重要的就是movtTo和close,如果是Style.FILL的话，不设置close,也没有区别，可是如果是STROKE模式，
         * 如果不设置close,图形不封闭。
         * 
         * 当然，你也可以不设置close，再添加一条线，效果一样。
         */
        canvas.drawPath(path1, paint);
        
        
        
        
        ///////////////////////////////////////第二列

        paint.setColor(Color.BLUE);

        paint.setStyle(Paint.Style.FILL);//设置实心

        canvas.drawCircle(120, 40, 30, paint);

        canvas.drawRect(90, 90, 150, 150, paint);

        canvas.drawRect(90, 170, 150, 200, paint);

        RectF re2 = new RectF(90, 220, 150, 250);

        canvas.drawOval(re2, paint);

        Path path2 = new Path();

        path2.moveTo(90, 330);

        path2.lineTo(150, 330);

        path2.lineTo(120, 270);

        path2.close();

        canvas.drawPath(path2, paint);

        Path path3 = new Path();

        path3.moveTo(90, 410);

        path3.lineTo(150, 410);

        path3.lineTo(135, 350);

        path3.lineTo(105, 350);

        path3.close();

        canvas.drawPath(path3, paint);
        
        
        ////////////////////////////////////////////////////第三列
        
        /*
         * LinearGradient shader = new LinearGradient(0, 0, endX, endY, new
         * int[]{startColor, midleColor, endColor},new float[]{0 , 0.5f,
         * 1.0f}, TileMode.MIRROR);
         * 参数一为渐变起初点坐标x位置，参数二为y轴位置，参数三和四分辨对应渐变终点
         * 其中参数new int[]{startColor, midleColor,endColor}是参与渐变效果的颜色集合， 
         * 其中参数new float[]{0 , 0.5f, 1.0f}是定义每个颜色处于的渐变相对位置， 这个参数可以为null，如果为null表示所有的颜色按顺序均匀的分布
         */
        Shader mShader = new LinearGradient(0, 0, 100, 100,

        new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW },

        null, Shader.TileMode.REPEAT);

        // Shader.TileMode三种模式

        // REPEAT:沿着渐变方向循环重复

        // CLAMP:如果在预先定义的范围外画的话，就重复边界的颜色

        // MIRROR:与REPEAT一样都是循环重复，但这个会对称重复

        paint.setShader(mShader);// 用Shader中定义定义的颜色来话

        canvas.drawCircle(200, 40, 30, paint);

        canvas.drawRect(170, 90, 230, 150, paint);

        canvas.drawRect(170, 170, 230, 200, paint);

        RectF re3 = new RectF(170, 220, 230, 250);

        canvas.drawOval(re3, paint);

        Path path4 = new Path();

        path4.moveTo(170, 330);

        path4.lineTo(230, 330);

        path4.lineTo(200, 270);

        path4.close();

        canvas.drawPath(path4, paint);

        Path path5 = new Path();

        path5.moveTo(170, 410);

        path5.lineTo(230, 410);

        path5.lineTo(215, 350);

        path5.lineTo(185, 350);

        path5.close();

        canvas.drawPath(path5, paint);
        
        //////////////////////////////////第4列

        paint.setTextSize(24);

        canvas.drawText("圆形", 240, 50, paint);

        canvas.drawText("正方形", 240, 120, paint);

        canvas.drawText("长方形", 240, 190, paint);

        canvas.drawText("椭圆形", 240, 250, paint);

        canvas.drawText("三角形", 240, 320, paint);

        canvas.drawText("梯形", 240, 390, paint);
	}
}
