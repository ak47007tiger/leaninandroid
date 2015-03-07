package com.imgdownloadcache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

public class FileStorage {

	boolean sdCardExist;
	File cacheDir;
	long maxSize = 1024 * 1024 * 4;//b -> kb -> mb
	Context context;
	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public FileStorage(Context context) {
		this.context = context;
		sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		File parent;
		if (sdCardExist) {
			parent = Environment.getExternalStorageDirectory();
		} else {
			parent = context.getCacheDir();
		}
		cacheDir = new File(parent, "cache");
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
	}

	public InputStream getInputStream(String url) {
		InputStream is = null;
		String fileName = urlToFileName(url);
		try {
			File f = new File(cacheDir, fileName);
			if(f.exists()){
				is = new FileInputStream(f);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return is;
	}

	public String urlToFileName(final String url) {
		String fileName = null;
		// 正式的办法：使用正则验证url是http还是ip，去掉协议头，提取文件名
		char first = url.charAt(0);
		if ('h' == first || 'f' == first) {
			fileName = url.substring(url.indexOf(":") + 1);
		}
		return fileName.replace('/', '-');
	}

	public void saveFile(String imageUrl, Bitmap bitmap) {
		long curSize = getAllFileSize();
		if(maxSize < curSize + bitmap.getByteCount()){
			File fileToBeDelete = getFileBeDelete();
			if(null != fileToBeDelete){
				fileToBeDelete.delete();
			}
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(cacheDir, urlToFileName(imageUrl)));
			bitmap.compress(CompressFormat.JPEG, 100, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private File getFileBeDelete() {
		File[] cacheFiles = cacheDir.listFiles();
		File toBeDel = null;
		if(cacheFiles.length > 0){
			toBeDel = cacheFiles[0];
		}
		for(File f : cacheFiles){
			if (f.length() > toBeDel.length()){
				toBeDel = f;
			}
		}
		return toBeDel;
	}

	public long getAllFileSize(){
		File[] cacheFiles = cacheDir.listFiles();
		long totalSize = 0;
		for(File f : cacheFiles){
			totalSize += f.length();
		}
		return totalSize;
	}

	public void clear() {
		File[] cacheFiles = cacheDir.listFiles();
		for(File f : cacheFiles){
			f.deleteOnExit();
		}
	}

}
