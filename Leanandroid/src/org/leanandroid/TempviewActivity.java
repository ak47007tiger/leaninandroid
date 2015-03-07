package org.leanandroid;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.start.util.MenuUtil;
import com.start.viewcontrollor.AdapterViewFlipperControllor;
import com.start.viewcontrollor.AutoCompletTVControllor;
import com.start.viewcontrollor.BaseControllor;
import com.start.viewcontrollor.CheckBoxControllor;
import com.start.viewcontrollor.ChronometerControllor;
import com.start.viewcontrollor.NotificationControllor;
import com.start.viewcontrollor.RadioButtonControllor;
import com.start.viewcontrollor.SpinnerControllor;
import com.start.viewcontrollor.ToggleButtonControllor;

public class TempviewActivity extends Activity {

	List<BaseControllor> controllors = new LinkedList<BaseControllor>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int id = getIntent().getBundleExtra("data").getInt("layoutid");
		setContentView(id);
//		getActionBar().setDisplayHomeAsUpEnabled(true);

		switch (id) {
		case R.layout.generalcontrol:
			new CheckBoxControllor(this);
			new RadioButtonControllor(this);
			new ToggleButtonControllor(this);
			new ChronometerControllor(this);
			new AutoCompletTVControllor(this);
			new SpinnerControllor(this);
			new AdapterViewFlipperControllor(this);
			// controllors.add(new CheckBoxControllor(this));
			break;
		case R.layout.notificationusing:
			new NotificationControllor(this);
			break;
		default:
			break;
		}
		/*
		 * View layout = getLayoutInflater().inflate(id, null); RelativeLayout
		 * tempview = (RelativeLayout) findViewById(R.layout.tempview); try{
		 * tempview.addView(layout); }catch(Exception e){ e.printStackTrace(); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tempview, menu);
		getMenuInflater().inflate(R.menu.menugeneral, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_menuactivity:
			MenuUtil.goToMain(this);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
