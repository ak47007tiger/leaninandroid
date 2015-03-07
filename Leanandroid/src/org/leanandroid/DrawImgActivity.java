package org.leanandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DrawImgActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw_img);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.draw_img, menu);
		return true;
	}

}
