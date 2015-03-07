package org.leanandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.imgdownloadcache.EasyImgDownloader;

public class GetNetImgActivity extends Activity{

	String[] urls = {
			"http://www.leewiart.com/userfiles/4941/736c63968f927ee481213e6c66d51372.jpg",
			"http://img.sj33.cn/uploads/allimg/201002/20100204184142708.jpg",
			"http://images.17173.com/2012/news/2012/12/14/pbb1214hjgz04.jpg",
			"http://pic.jschina.com.cn/0/11/18/81/11188196_322841.jpg",
			"http://pic.jschina.com.cn/0/11/18/81/11188192_927301.jpg",
			"http://pic8.nipic.com/20100722/759916_162219078521_2.jpg"
	};
	LinearLayout linearLayout;
	EasyImgDownloader easyDownloader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loadnetimglayout);
		easyDownloader = EasyImgDownloader.sharedDownloader(this);
		linearLayout = (LinearLayout) findViewById(R.id.imgcontainer);
		for(int i = 0; i < urls.length; i++){
			ImageView imageView = (ImageView) linearLayout.getChildAt(i);
			easyDownloader.setImgView(urls[i], imageView, R.drawable.face);
		}
	}
	public void clearImgCache(View v){
		easyDownloader.getImgCache().clear();
	}
	public void clearFileCache(View v){
		easyDownloader.getFileStorage().clear();
	}
	public void resetimg(View v){
		for(int i = 0; i < urls.length; i++){
			ImageView imageView = (ImageView) linearLayout.getChildAt(i);
			easyDownloader.setImgView(urls[i], imageView, R.drawable.face);
		}
	}
}
