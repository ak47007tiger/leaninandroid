package org.leanandroid;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	Button bt;
	EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LayoutInflater inflater = getLayoutInflater();
		bt = (Button) this.findViewById(R.id.button1);
		et = (EditText) MainActivity.this.findViewById(R.id.editText1);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				System.out.println("click");
				et.setText("ok");
//				new Thread(new ConnSocket()).start();
				new Thread(new ConnTomcat()).start();
			}
		});
	}

	class ConnTomcat implements Runnable {
		@Override
		public void run() {
			URL url;
			try {
				url = new URL(
						"http://10.1.217.248:8080/androidserver/TestMsgServlet");
				// url = new URL("http://www.baidu.com");
				HttpURLConnection uc = (HttpURLConnection) url.openConnection();
				uc.connect();
				InputStream ins = uc.getInputStream();
				byte[] buf = new byte[256];
				ins.read(buf);
				ins.close();
				String s = new String(buf, "utf-8");
				Message msg = new Message();
				msg.obj = s;
				msg.what = 0;
				handler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
class ConnSocket implements Runnable{
	@Override
	public void run() {
		Socket client = new Socket();
		try {
			client.connect(new InetSocketAddress(InetAddress.getByName("10.1.217.248"), 7071));
			InputStream ins = client.getInputStream();
			byte[]buffer = new byte[128];
			ins.read(buffer);
			String s = new String(buffer,"utf-8");
			Message msg = new Message();
			msg.obj = s;
			msg.what = 0;
			handler.sendMessage(msg);
			ins.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				String s = (String) msg.obj;
				// et.setText(s.toCharArray(),0,s.length());
				et.setText(s);
				break;

			default:
				break;
			}
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
