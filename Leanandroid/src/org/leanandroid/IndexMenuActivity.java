package org.leanandroid;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.start.util.AdapterUtil;

public class IndexMenuActivity extends Activity {

	private static String[] keys = {"title","remark","className","layoutid"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simpleadapter);
		List<Map<String, Object>> contents = new LinkedList<Map<String,Object>>();
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"gridView","测试gridView","org.leanandroid.GridviewActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"login","测试login","org.leanandroid.LoginActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"expandablelistview","测试ExpandlistviewActivity",
				"org.leanandroid.ExpandlistviewActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"ViewswitcherActivity","测试ViewswitcherActivity",
				"org.leanandroid.ViewswitcherActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"ExtendstabhostActivity","测试ExtendstabhostActivity",
				"org.leanandroid.ExtendstabhostActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"NotextendstabActivity","测试NotextendstabActivity",
				"org.leanandroid.NotextendstabActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"ActionbarnavActivity","测试ActionbarnavActivity",
				"org.leanandroid.ActionbarnavActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"ActionbarnavlistActivity","测试ActionbarnavlistActivity",
				"org.leanandroid.ActionbarnavlistActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"CustomActionBar","测试CustomActionBar",
				"org.leanandroid.CustomActionBar",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new String[]{"AlertDialogActivity","测试AlertDialogActivity",
				"org.leanandroid.AlertDialogActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"PopupWindowActivity","测试PopupWindowActivity",
				"org.leanandroid.PopupWindowActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"ManyMenuActivity","测试ManyMenuActivity",
				"org.leanandroid.ManyMenuActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"PaintActivity","测试PaintActivity",
				"org.leanandroid.PaintActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"PreferencesActivity","测试PreferencesActivity",
				"org.leanandroid.PreferencesActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"FileOpsActivity","测试FileOpsActivity",
				"org.leanandroid.FileOpsActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"UseLoadingHelperActivity","测试UseLoadingHelperActivity",
				"org.leanandroid.UseLoadingHelperActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"SqliteActivity","测试SqliteActivity",
				"org.leanandroid.SqliteActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"CustomViewGroupContainer","测试CustomViewGroupContainer",
				"loc.customview.viewgroup.CustomViewGroupContainer",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"TempActivity","测试TempActivity",
				"org.leanandroid.TempActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"UseService","测试UseService",
				"org.leanandroid.UseService",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"ImgAndAnima","测试ImgAndAnima",
				"org.leanandroid.ImgAndAnima",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"CirCleMenuActivity","测试CirCleMenuActivity",
				"org.leanandroid.CirCleMenuActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"GetNetImgActivity","测试GetNetImgActivity",
				"org.leanandroid.GetNetImgActivity",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"FlowWindow","测试FlowWindow",
				"com.window.FlowWindow",null}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"GesturePswActivity","测试GesturePswActivity",
				"com.gesturepsw.GesturePswActivity",null}));
		
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"scrollview","测试scrollview",
				"org.leanandroid.TempviewActivity",R.layout.scrollview}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"tablelayout","测试tablelayout",
				"org.leanandroid.TempviewActivity",R.layout.tablelayout}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"generalcontrol","测试generalcontrol",
				"org.leanandroid.TempviewActivity",R.layout.generalcontrol}));
		contents.add(AdapterUtil.createMenuItem(keys, new Object[]{"notificationusing","测试notificationusing",
				"org.leanandroid.TempviewActivity",R.layout.notificationusing}));
		
		ListView listView = (ListView) findViewById(R.id.simpleadapter_listmenu);
		listView.setAdapter(new ListMenuAdapter(this,contents));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView myListView = (ListView) arg0;
				HashMap<String, Object> item = 
						(HashMap<String, Object>) myListView.getItemAtPosition(arg2);
				String title = (String) item.get("title");
				String remark = (String) item.get("remark");
				String className = (String) item.get("className");
				Class<?> classObj = null;
				Intent intent = null;
				Bundle data = null;
				try {
					classObj = Class.forName(className);
					data = new  Bundle();
					intent = new Intent(IndexMenuActivity.this, classObj);
					intent.putExtra("data", data);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if(null != item.get("layoutid")){
					Integer layoutid = null;
					layoutid = (Integer) item.get("layoutid");
					data.putInt("layoutid", layoutid);
				}
				try{
					IndexMenuActivity.this.startActivity(intent);
				}catch(Exception e){
					Log.d("indexMenu", e.getMessage());
				}
//				finish();
			}
		});
		listView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	class ListMenuAdapter extends BaseAdapter{
		List<Map<String,Object>> content;
		public ListMenuAdapter(Context context, List<Map<String,Object>> content) {
			this.content = content;
		}
		@Override
		public int getCount() {
			return content.size();
		}

		@Override
		public Object getItem(int arg0) {
			return content.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ViewHolder vh;
			if(null == arg1){
				arg1 = LayoutInflater.from(IndexMenuActivity.this).inflate(R.layout.simpleadapteritem, null);
				vh = new ViewHolder();
				vh.title = (TextView) arg1.findViewById(R.id.listmenuitem_title);
				vh.remark = (TextView) arg1.findViewById(R.id.listmenuitem_remark);
				arg1.setTag(vh);
			}else{
				vh = (ViewHolder) arg1.getTag();
			}
			vh.title.setText(content.get(arg0).get("title").toString());
			vh.remark.setText(content.get(arg0).get("remark").toString());
			return arg1;
		}
		class ViewHolder{
			TextView title;
			TextView remark;
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simpleadapter, menu);
		return true;
	}

}
