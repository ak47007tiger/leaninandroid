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
		// ����TabActivity��getTabHost()������ȡTabHost����
		TabHost tabHost = getTabHost();

		// ����ʹ��TabHost����
		LayoutInflater.from(this).inflate(R.layout.extendstabhost,
				tabHost.getTabContentView(), true);

		// ��ӵ�һ����ǩҳ
		tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("�ѽӵ绰")
				.setContent(R.id.extendstabhost_tab01));

		// ��ӵڶ�����ǩҳ,�������ǩ�����һ��ͼƬ
		tabHost.addTab(tabHost
				.newTabSpec("tab02")
				.setIndicator("δ�ӵ绰",
						getResources().getDrawable(R.drawable.upgreen))
				.setContent(R.id.extendstabhost_tab02));

		// ��ӵ�������ǩҳ
		tabHost.addTab(tabHost.newTabSpec("tab03").setIndicator("�Ѳ��绰")
				.setContent(R.id.extendstabhost_tab03));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extendstabhost, menu);
		return true;
	}

}
