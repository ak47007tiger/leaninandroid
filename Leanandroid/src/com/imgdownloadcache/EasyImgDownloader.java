package com.imgdownloadcache;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class EasyImgDownloader extends ImgDownloader{

	protected EasyImgDownloader(Context context) {
		super(context);
		imgCache = ImgCache.sharedImgCache();
		fileStorage = new FileStorage(context);
	}
	private static EasyImgDownloader downloader;
	public static EasyImgDownloader sharedDownloader(Context context){
		if(null == downloader){
			downloader = new EasyImgDownloader(context);
			downloader.reset(2);
		}
		return downloader;
	}
	public void setImgView(final String url, final ImageView imgView, final int failedImg) {
		Bitmap bm = null;
		while (true) {
			bm = imgCache.get(url);
			if (null != bm) {
				break;
			}
			InputStream is = fileStorage.getInputStream(url);
			if (null != is) {
				bm = BitmapFactory.decodeStream(is);
				imgCache.put(url, bm);
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			download(url, new Runnable() {
				@Override
				public void run() {
					((Activity) context).runOnUiThread(new Runnable() {
						@Override
						public void run() {
							imgView.setImageBitmap(imgCache.get(url));
						}
					});
				}
			}, new Runnable() {
				@Override
				public void run() {
					((Activity) context).runOnUiThread(new Runnable() {
						@Override
						public void run() {
							imgView.setImageResource(failedImg);
						}
					});
				}
			});
			return;
		}
		imgView.setImageBitmap(bm);
	}
}
