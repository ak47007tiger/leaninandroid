package org.leanandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.TextView;

public class ManyMenuActivity extends Activity implements OnClickListener{

	TextView asyn_result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_context_menu);
		registerForContextMenu(findViewById(R.id.tv_contextmenu));
		registerForContextMenu(findViewById(R.id.tv_popmenu));
		findViewById(R.id.tv_popmenu).setOnClickListener(this);
		findViewById(R.id.asyntask_findword).setOnClickListener(this);
		asyn_result = (TextView) findViewById(R.id.asyntask_result);
		findViewById(R.id.start_user_con).setOnClickListener(this);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater menuInflater = new MenuInflater(this);
		menuInflater.inflate(R.menu.contextmenu, menu);
		menu.setHeaderIcon(R.drawable.upgray);
		menu.setHeaderTitle("context menu");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		item.setChecked(true);
		switch (item.getItemId()) {
		case R.id.item1:
			break;
		case R.id.item2:

			break;
		case R.id.item3:
			break;
		default:

			break;
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.context_menu, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tv_popmenu:
			PopupMenu popupMenu = new PopupMenu(this, arg0);
			popupMenu.inflate(R.menu.pupmenu);
			popupMenu.show();
			break;
		case R.id.asyntask_findword:
			new FindWords_AsynTask().execute("");
			break;
		case R.id.start_user_con:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setType("vnd.android.cursor.item/phone");
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	class FindWords_AsynTask extends AsyncTask<String, Integer, String>{
		long init;
		@Override
		protected void onPreExecute() {
			init = System.currentTimeMillis();
		}
		@Override
		protected String doInBackground(String... arg0) {
			long start;
			start = System.currentTimeMillis();
			Log.d("---do - init", start - init + "");
			int i = 0;
			publishProgress(i);
			try {
				while(++i < 100){
					Thread.sleep(10);
					publishProgress(i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			long time = end - start;
			Log.d("before end", end + "-" + start + "=" + time);
			return "using time:" + time + "ms";
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			Message msg = handler.obtainMessage(update_result, values[0] + "");
			handler.sendMessage(msg);
		}
		@Override
		protected void onPostExecute(String result) {
			Message msg = handler.obtainMessage(update_result, result);
			Log.d("-----end", result);
			handler.sendMessage(msg);
		}
	}
	final int update_result = 0;
	Handler handler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			switch (msg.what) {
			case update_result:
				asyn_result.setText((String)msg.obj + "%");
				break;
			default:
				break;
			}
		}
	};
}
