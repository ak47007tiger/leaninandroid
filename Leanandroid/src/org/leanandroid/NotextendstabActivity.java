package org.leanandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.start.util.MenuUtil;

public class NotextendstabActivity extends Activity {

	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notextendstab);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		tabHost = (TabHost) findViewById(R.id.notextendstab_tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost
				.newTabSpec("tab1")
				.setIndicator("1",
						getResources().getDrawable(R.drawable.upgreen))
				.setContent(R.id.notextendstab_view1));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("2")
				.setContent(R.id.notextendstab_view2));
		Button btn = new Button(this);
		
		btn.setText("btn");
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(NotextendstabActivity.this, "test", 1000).show();
				tabHost.setCurrentTab(2);
			}
		});
		btn.setHeight(20);
		btn.setWidth(50);
		btn.setBackgroundColor(0xedf3de);
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("3")
				.setContent(R.id.notextendstab_view3));
		tabHost.addView(btn);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			MenuUtil.goToMain(this);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.notextendstab, menu);
		return true;
	}

}
