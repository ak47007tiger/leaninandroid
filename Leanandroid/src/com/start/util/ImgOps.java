package com.start.util;

import org.leanandroid.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImgOps {

	public void test1(Activity activity){
        Resources res=activity.getResources();
        Drawable drawable = res.getDrawable(R.drawable.windandsord);
        //实际上这是一个BitmapDrawable对象
        BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
        //可以在调用getBitmap方法，得到这个位图
        Bitmap bitmap=bitmapDrawable.getBitmap();
	}
}
