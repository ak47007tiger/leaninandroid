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
        //ʵ��������һ��BitmapDrawable����
        BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
        //�����ڵ���getBitmap�������õ����λͼ
        Bitmap bitmap=bitmapDrawable.getBitmap();
	}
}
