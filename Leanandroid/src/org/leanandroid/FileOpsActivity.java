package org.leanandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class FileOpsActivity extends Activity {

	TextView tv_textContainer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_ops);
		tv_textContainer = (TextView) findViewById(R.id.textcontainer);
		File filesDir = getFilesDir();
		tv_textContainer.setText(getAllFileName(filesDir).toString());
		File newFile = new File(filesDir ,"newfile.txt");
		if(!newFile.exists()){
			try {
				FileOutputStream fo = new FileOutputStream(newFile);
				fo.write(new String("test io").getBytes("utf-8"));
				fo.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String appendStr = null;
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)){
			appendStr = "有sd卡，并且能读、写\n";
		}else{
			appendStr = "没有sd卡\n";
		}
		tv_textContainer.append(appendStr);
		tv_textContainer.append(Environment.getExternalStorageState() + "\n");
		tv_textContainer.append(Environment.getExternalStorageDirectory().getPath()+ "\n");
		tv_textContainer.append(Environment.getDownloadCacheDirectory().getPath()+ "\n");
		tv_textContainer.append(Environment.getRootDirectory().getPath()+ "\n");
		tv_textContainer.append(Environment.getExternalStorageDirectory().list().length + "\n");
		try{
			new Thread(new Runnable(){
				@Override
				public void run() {
					ModalDialog.showDialog(FileOpsActivity.this);
					filesStr = getAllFileName(Environment.getExternalStorageDirectory());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ModalDialog.closeDialog(FileOpsActivity.this);
					handler.sendEmptyMessage(0);
				}
			}){
				
			}.start();
		}catch(Exception e){
			Log.d("file op s", e.getMessage());
		}
		tv_textContainer.append("over\n");
	}
	StringBuffer filesStr;

	Handler handler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			tv_textContainer.append(filesStr);
			TextView v = (TextView) findViewById(R.id.loading);
			v.setText("加载完成");
		};
	};
	private StringBuffer getAllFileName(File root){
		StringBuffer sb = new StringBuffer();
		sb.append(root.getPath() + "\n");
		if(root.getName().matches(new String("\\.{1}.+"))){
			return sb;
		}
		if(root.isDirectory()){
			File[] children = root.listFiles();
			for(File child : children){
				sb.append(getAllFileName(child));
			}
		}
		return sb;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.file_ops, menu);
		return true;
	}

}
