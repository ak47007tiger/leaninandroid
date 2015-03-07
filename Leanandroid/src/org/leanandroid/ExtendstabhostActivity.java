package org.leanandroid;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

public class ExtendstabhostActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 调用TabActivity的getTabHost()方法获取TabHost对象
		TabHost tabHost = getTabHost();

		// 设置使用TabHost布局
		LayoutInflater.from(this).inflate(R.layout.extendstabhost,
				tabHost.getTabContentView(), true);

		// 添加第一个标签页
		tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("已接电话")
				.setContent(R.id.extendstabhost_tab01));

		// 添加第二个标签页,并在其标签上添加一个图片
		tabHost.addTab(tabHost
				.newTabSpec("tab02")
				.setIndicator("未接电话",
						getResources().getDrawable(R.drawable.upgreen))
				.setContent(R.id.extendstabhost_tab02));

		// 添加第三个标签页
		tabHost.addTab(tabHost.newTabSpec("tab03").setIndicator("已拨电话")
				.setContent(R.id.extendstabhost_tab03));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extendstabhost, menu);
		return true;
	}

}
