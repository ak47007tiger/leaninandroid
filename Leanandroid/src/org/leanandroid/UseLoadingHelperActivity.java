package org.leanandroid;

import loc.loadingview.LoadingHelper;
import loc.loadingview.SimpleLoadSuccessHelperImpl;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class UseLoadingHelperActivity extends Activity {

	LoadingHelper loadingHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_use_loading_helper);
		RelativeLayout parentContainer = (RelativeLayout) findViewById(R.id.container);
		loadingHelper = new LoadingHelper(handler, parentContainer,
				new SimpleLoadSuccessHelperImpl(this), 0, 1);
		loadingHelper.doLoad();
	}
	Handler handler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				try {
					loadingHelper.replace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Toast.makeText(UseLoadingHelperActivity.this, "加载完成", 2000).show();
				break;
			case 1:
				Toast.makeText(UseLoadingHelperActivity.this, "加载失败", 2000).show();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.use_loading_helper, menu);
		return true;
	}

}
