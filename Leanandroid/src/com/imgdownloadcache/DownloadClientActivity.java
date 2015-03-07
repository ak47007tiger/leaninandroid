package com.imgdownloadcache;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class DownloadClientActivity extends Activity {

	ImgDownloader imgDownloader;
	protected ImgCache imgCache;
	protected FileStorage fileStorage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imgDownloader = ImgDownloader.sharedFileDownloader(this);
		imgDownloader.reset(2);
		imgCache = ImgCache.sharedImgCache();
		fileStorage = new FileStorage(this);
		imgDownloader.setImgCache(imgCache);
		imgDownloader.setFileStorage(fileStorage);
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
			imgDownloader.download(url, new Runnable() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							imgView.setImageBitmap(imgCache.get(url));
						}
					});
				}
			}, new Runnable() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		imgDownloader.destory();
		/*imgCache.clear();*/
	}
}
