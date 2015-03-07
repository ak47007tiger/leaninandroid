package com.imgdownloadcache;

import java.util.Iterator;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImgCache {

	LruCache<String, Bitmap> mMemoryCache;

	private static ImgCache imgCache;

	protected static int defaultSize = 1024 * 1024 * 3;//3MB 
	private ImgCache() {
		mMemoryCache = new LruCache<String, Bitmap>(defaultSize);
	}

	public static ImgCache sharedImgCache() {
		if (null == imgCache) {
			imgCache = new ImgCache();
		}
		return imgCache;
	}

	public Bitmap get(String url) {
		synchronized (mMemoryCache) {
			return mMemoryCache.get(url);
		}
	}

	public void put(String imageUrl, Bitmap bitmap) {
		synchronized (mMemoryCache) {
			mMemoryCache.put(imageUrl, bitmap);
		}
	}

	public void destory(){
		Bitmap[] imgs = new Bitmap[mMemoryCache.size()];
		int i = 0;
		synchronized (mMemoryCache) {
			Iterator<String> keys = mMemoryCache.snapshot().keySet().iterator();
			while (keys.hasNext()) {
				imgs[i++] = mMemoryCache.get(keys.next());
			}
			mMemoryCache.evictAll();
			mMemoryCache = null;
		}
		for(Bitmap bitmap : imgs){
			if(bitmap.isRecycled()){
				bitmap.recycle();
			}
		}
	}
	public void clear() {
		synchronized (mMemoryCache) {
			Iterator<String> keys = mMemoryCache.snapshot().keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Bitmap bitmap = mMemoryCache.get(key);
				mMemoryCache.remove(key);
				if (!bitmap.isRecycled()) {
					bitmap.recycle();
				}
			}
		}
	}

}
