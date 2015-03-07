package com.imgdownloadcache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImgDownloader {

	public static final int NOTSTART = 0;
	public static final int DOWNLOADING = 1;
	public static final int COMPLETED = 2;
	public boolean shutdown = false;
	protected FileStorage fileStorage;
	protected ImgCache imgCache;
	protected int connectTimeout = 10000;
	protected int readTimeout = 10000;
	protected Context context;
	protected ImgDownloader(Context context) {
		this.context = context;
	}

	private static ImgDownloader fileDownloader;

	static ImgDownloader sharedFileDownloader(Context context) {
		if (null == fileDownloader) {
			fileDownloader = new ImgDownloader(context);
		}
		return fileDownloader;
	}

	public void destory() {
		shutdown = true;
		for (DownloadFileThread thread : threadPool) {
			synchronized (thread.threadLock) {
				thread.threadLock.notify();
			}
		}
		threadPool.clear();
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public void setImgCache(ImgCache imgCache) {
		this.imgCache = imgCache;
	}

	public void setFileStorage(FileStorage fileStorage) {
		this.fileStorage = fileStorage;
	}

	public FileStorage getFileStorage() {
		return fileStorage;
	}

	public ImgCache getImgCache() {
		return imgCache;
	}

	List<DownloadFileThread> threadPool = new ArrayList<ImgDownloader.DownloadFileThread>();

	class DownloadFileThread extends Thread {
		Queue<String> urls = new LinkedBlockingDeque<String>(20);
		Map<String, Runnable> successTasks = new HashMap<String, Runnable>();
		Map<String, Runnable> failTasks = new HashMap<String, Runnable>();
		String curUrl;
		File tempFile;
		public void addTask(String url, Runnable success, Runnable fail) {
			urls.add(url);
			successTasks.put(url, success);
			failTasks.put(url, fail);
			synchronized (threadLock) {
				threadLock.notifyAll();
			}
		}

		public DownloadFileThread() {
		}
		Object threadLock = new Object();

		@Override
		public void run() {
			while (true) {
				// 正在下载的时候关闭
				if (shutdown) {
					break;
				}
				if (urls.size() == 0) {
					synchronized (threadLock) {
						try {
							threadLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				// 等待唤醒的时候关闭
				if (shutdown) {
					break;
				}
				if (0 < urls.size()) {
					Bitmap bitmap;
					bitmap = null;
					InputStream is = null;
					try {
						curUrl = urls.poll();
						URL imageUrl = new URL(curUrl);
						URLConnection conn = imageUrl.openConnection();
						conn.setConnectTimeout(connectTimeout);
						conn.setReadTimeout(readTimeout);
						is = conn.getInputStream();
						bitmap = decode(is);
						imgCache.put(curUrl, bitmap);
						fileStorage.saveFile(curUrl, bitmap);
						successTasks.get(curUrl).run();
					} catch (Exception e) {
						failTasks.get(curUrl).run();
						e.printStackTrace();
					} finally {
						if (null != is) {
							try {
								is.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

		public File newTempFile() {
			tempFile = new File(context.getCacheDir(), "temp" + Thread.currentThread().getId());
			return tempFile;
		}
		public Bitmap decode(InputStream is) {
			OutputStream out = null;
			DownloadFileThread thread = null;
			try {
				thread = (DownloadFileThread) Thread.currentThread();
				out = new FileOutputStream(thread.newTempFile());
				byte[] buffer = new byte[1024 * 512];
				int count;
				while(-1 != (count = is.read(buffer))){
					out.write(buffer, 0, count);
				}
				Bitmap bitmap = decode(thread.tempFile);
				thread.tempFile.delete();
				return bitmap;
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			} finally{
				try {
					if(null != out){
						out.close();
					}
					if(null != is){
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		public Bitmap decode(File file) {
			try {
				// decode image size
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;
//				BitmapFactory.decodeStream(new FileInputStream(file), null, o);
				BitmapFactory.decodeFile(file.getPath(), o);
				// Find the correct scale value. It should be the power of 2.
				final int REQUIRED_SIZE = 70;
				int width_tmp = o.outWidth, height_tmp = o.outHeight;
				int scale = 1;
				while (true) {
					if (width_tmp / 2 < REQUIRED_SIZE
							|| height_tmp / 2 < REQUIRED_SIZE)
						break;
					width_tmp /= 2;
					height_tmp /= 2;
					scale *= 2;
				}

				// decode with inSampleSize
				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale;
				return BitmapFactory.decodeStream(new FileInputStream(file), null,
						o2);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public void download(String url, Runnable success, Runnable fail) {
		getNotBusyThread().addTask(url, success, fail);
	}

	private DownloadFileThread getNotBusyThread() {
		DownloadFileThread downloadFileThread = null;
		Queue<String> urlsMax = null;
		for (int index = 0; index < threadPool.size(); index++) {
			DownloadFileThread tempThread = threadPool.get(index);
			Queue<String> urls = tempThread.urls;
			synchronized (urls) {
				if (null != urlsMax) {
					synchronized (urls) {
						if (urlsMax.size() > urls.size()) {
							urlsMax = urls;
							downloadFileThread = tempThread;
						}
					}
				} else {
					urlsMax = urls;
					downloadFileThread = tempThread;
				}
			}
		}
		return downloadFileThread;
	}

	public void reset(int sum) {
		if(!shutdown){
			destory();
		}
		shutdown = false;
		for (int count = 0; count < sum; count++) {
			DownloadFileThread downloadFileThread = new DownloadFileThread();
			downloadFileThread.start();
			threadPool.add(downloadFileThread);
		}
	}

	public void download(String img_str_url1) {

	}

	public int getStatus(String url) {
		for (int index = 0; index < threadPool.size(); index++) {
			DownloadFileThread downloadFileThread = threadPool.get(index);
			Queue<String> urls = downloadFileThread.urls;
			synchronized (urls) {
				if (downloadFileThread.curUrl.equals(url)) {
					return DOWNLOADING;
				}
				if (urls.contains(url)) {
					return NOTSTART;
				}
			}
		}
		return COMPLETED;
	}

	public void cancle(String url) {
		for (int index = 0; index < threadPool.size(); index++) {
			DownloadFileThread downloadFileThread = threadPool.get(index);
			Queue<String> urls = downloadFileThread.urls;
			synchronized (urls) {
				if (urls.contains(url)) {
					urls.remove(url);
					break;
				}
			}
		}
	}

}
