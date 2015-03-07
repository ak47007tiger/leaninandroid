package org.leanandroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.leanandroid.service.TestService;
import org.leanandroid.service.TestService.TestServiceBinder;

import com.start.util.AdapterUtil;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UseService extends Activity {

	ListView menus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useservice);
		menus = (ListView) findViewById(R.id.service_op_menu);
		menus.setOnItemClickListener(listener);
		contents.add(AdapterUtil.createMenuItem(
				new String[] { "menuName", "id" }, new Object[] {
						"绑定test service, 不创建", 0 }));
		contents.add(AdapterUtil.createMenuItem(
				new String[] { "menuName", "id" }, new Object[] {
						"绑定test service, 并创建", 1 }));
		contents.add(AdapterUtil.createMenuItem(
				new String[] { "menuName", "id" }, new Object[] {
						"启动test service", 2 }));
		contents.add(AdapterUtil.createMenuItem(
				new String[] { "menuName", "id" }, new Object[] {
						"停止test service", 3 }));
		contents.add(AdapterUtil.createMenuItem(
				new String[] { "menuName", "id" }, new Object[] {
						"解除绑定test service", 4 }));
		contents.add(AdapterUtil.createMenuItem(
				new String[] { "menuName", "id" }, new Object[] {
						"获得test service名字", 5 }));
		menus.setAdapter(new MenuAdapter(this, contents));
		service = new Intent();
		service.setAction(TestService.action);
	}
	static final String tag = "UseService";
	ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Log.i(tag, "onServiceDisconnected");
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			binder = (TestServiceBinder) arg1;
			Log.i(tag, "onServiceConnected");
		}
	};
	TestServiceBinder binder;
	Intent service;
	OnItemClickListener listener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Map<String, Object> content = contents.get(arg2);
			switch ((Integer) (content.get("id"))) {
			case 0:
				bindService(service, conn, 0);
				break;
			case 1:
				bindService(service, conn, BIND_AUTO_CREATE);
				break;
			case 2:
				startService(service);
				break;
			case 3:
				stopService(service);
				break;
			case 4:
				unbindService(conn);
				break;
			case 5:
				binder.printfName();
				break;
			}
		}
	};
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();

	class MenuAdapter extends BaseAdapter {
		public MenuAdapter(Context context, List<Map<String, Object>> contents) {
			this.contents = contents;
			this.context = context;
		}

		Context context;
		List<Map<String, Object>> contents;

		@Override
		public int getCount() {
			return this.contents.size();
		}

		@Override
		public Object getItem(int arg0) {
			return this.contents.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			TextView name;
			if (null == arg1) {
				View v = LayoutInflater.from(context).inflate(
						R.layout.useservice_item, null);
				name = (TextView) v.findViewById(R.id.menuname);
				v.setTag(name);
				arg1 = v;
			} else {
				name = (TextView) arg1.getTag();
			}
			name.setText(this.contents.get(arg0).get("menuName").toString());
			return arg1;
		}
	}
}
