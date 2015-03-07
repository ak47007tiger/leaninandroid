package org.leanandroid;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.start.util.AdapterUtil;
import com.start.util.MenuUtil;

public class GridviewActivity extends Activity {

	private static String[] keys = {"title","remark"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		List<Map<String, Object>> contents = new LinkedList<Map<String,Object>>();
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"gridView","this is gridview"}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"gridView","����gridView"}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"login","����login"}));
		SimpleAdapter adapter = new SimpleAdapter(this,contents, R.layout.gridviewitem,
				new String[] {"title", "remark" }, 
				new int[] {R.id.gridviewitem_title, R.id.gridviewitem_remark});
		GridView gridView = (GridView)findViewById(R.id.gridview);
		gridView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gridview, menu);
		getMenuInflater().inflate(R.menu.menugeneral, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_menuactivity:
				MenuUtil.goToMain(this);
				break;
				default:
					return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
