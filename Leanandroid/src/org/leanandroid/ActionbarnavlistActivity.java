package org.leanandroid;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.start.helpclass.DumyFragment;
import com.start.util.MenuUtil;

public class ActionbarnavlistActivity extends Activity{
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
	ActionBar actionBar;
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
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		OnNavigationListener navListener = new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int arg0, long arg1) {
				Fragment fragment = new DumyFragment();
				Bundle args = new Bundle();
				args.putInt(DumyFragment.arg_section_num, arg0);
				fragment.setArguments(args);
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.fragmentcontainer,fragment);
				ft.commit();
				return true;
			}
		};
		actionBar.setListNavigationCallbacks(
				new ArrayAdapter<String>(this, 
						android.R.layout.simple_list_item_1, 
						android.R.id.text1,
						new String[]{"Ò»","¶þ","Èý"}), 
						navListener);
	}
}
