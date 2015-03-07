package org.leanandroid;

import java.io.InputStream;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ModalDialog extends Activity{
	public static final String action = "org.leanandroid.close_modaldlg";
	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InputStream ins = ClassLoader.getSystemResourceAsStream("");
		setContentView(R.layout.modaldialog);
		IntentFilter filter = new IntentFilter();
		filter.addAction(action);
		registerReceiver(receiver, filter);
		findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	private BroadcastReceiver receiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Log.d("ModalDialog", "finish");
			ModalDialog.this.finish();
		}
	};
	public static void showDialog(Context context){
		Intent intent = new Intent(context, ModalDialog.class);
		context.startActivity(intent);
	}
	public static void closeDialog(Context context){
		Intent broad = new Intent(ModalDialog.action);
		context.sendBroadcast(broad);
	}
}
