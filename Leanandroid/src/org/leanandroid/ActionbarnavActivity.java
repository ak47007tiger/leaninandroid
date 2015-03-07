package org.leanandroid;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.start.helpclass.DumyFragment;
import com.start.util.MenuUtil;

public class ActionbarnavActivity extends Activity {

	ActionBar actionBar;
	TabListener listener;
	static String selected_item = "selected_item";
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(selected_item, 
				getActionBar().getSelectedNavigationIndex());
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if(savedInstanceState.containsKey(selected_item)){
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(selected_item));
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbarnav);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		listener = new TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				
			}
			
			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
				Fragment fragment = new DumyFragment();
				Bundle args = new Bundle();
				args.putInt(DumyFragment.arg_section_num, arg0.getPosition());
				fragment.setArguments(args);
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.fragmentcontainer,fragment);
				ft.commit();
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				
			}
		};
		actionBar.addTab(actionBar
				.newTab()
				.setText("��1����ǩ")
				.setIcon(R.drawable.upgreen)
				.setTabListener(listener));
		actionBar.addTab(actionBar
				.newTab()
				.setText("��2����ǩ")
				.setTabListener(listener));
		actionBar.addTab(actionBar
				.newTab()
				.setText("��3����ǩ")
				.setIcon(R.drawable.upgreen)
				.setTabListener(listener));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbarnav, menu);
		return true;
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
}
