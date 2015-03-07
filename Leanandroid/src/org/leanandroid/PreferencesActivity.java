package org.leanandroid;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.start.util.ListenerUtil;
import com.start.util.SharedPrefsUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class PreferencesActivity extends Activity implements OnClickListener {

	TextView allXml;
	EditText appName;
	EditText name_preference;
	EditText key;
	EditText value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);
		allXml = (TextView) findViewById(R.id.allxml);
		appName = (EditText) findViewById(R.id.appname);
		name_preference = (EditText)findViewById(R.id.name_preference);
		key = (EditText) findViewById(R.id.preferences_key);
		value = (EditText) findViewById(R.id.preferences_value);
		ListenerUtil.setSameOnClickListener(
				new int[]{R.id.add_preference,R.id.del_preference,
						R.id.update_preference,R.id.search_preference}, this, this);
	}
	public static final String PATH = "/data/data/org.leanandroid/shared_prefs/Database.xml";
	/** 获取存储文件的数据 */  
    private String print() {  
        StringBuffer buff = new StringBuffer();  
        try {  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    new FileInputStream(PATH)));  
            String str;  
            while ((str = reader.readLine()) != null) {  
                buff.append(str + "/n");  
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return buff.toString();  
    }  
	private void updateTvXml(){
		allXml.setText(print());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.preferences, menu);
		return true;
	}
	private static final String defaultName_preference = "setting";
	private String myappnamestr = "org.leanandroid";
	private void add(){
		String appNameStr = appName.getText().toString(); 
		String name_preferenceStr = name_preference.getText().toString();
		if(null != name_preferenceStr && 0 < name_preferenceStr.length()){
			
		}else{
			name_preferenceStr = defaultName_preference;
		}
		if(null != appNameStr && 
				0 < appNameStr.length() && 
				!myappnamestr.equals(appNameStr)){
			SharedPrefsUtil.putValue(this, 
					name_preferenceStr, Context.MODE_WORLD_READABLE, 
					key.getText().toString(), value.getText().toString());
		}else{
			SharedPrefsUtil.putValue(this, 
					name_preferenceStr, Context.MODE_PRIVATE, 
					key.getText().toString(), value.getText().toString());
		}
	}
	private void search(){
		String appNameStr = appName.getText().toString(); 
		String name_preferenceStr = name_preference.getText().toString();
		String keyStr = key.getText().toString();
		
		if(null != name_preferenceStr && 0 < name_preferenceStr.length()){
			
		}else{
			name_preferenceStr = defaultName_preference;
		}
		if(null != appNameStr && 
				0 < appNameStr.length() && 
				!myappnamestr.equals(appNameStr)){
			SharedPrefsUtil.getValue(this, name_preferenceStr, Context.MODE_WORLD_READABLE, keyStr, "");
		}else{
			String valStr = SharedPrefsUtil.getValue(this, name_preferenceStr, Context.MODE_PRIVATE, keyStr, "");
			value.setText(valStr);
		}
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.add_preference:
			add();
			updateTvXml();
			break;
		case R.id.del_preference:
			updateTvXml();
			break;
		case R.id.update_preference:
			add();
			updateTvXml();
			break;
		case R.id.search_preference:
			search();
			break;
		}
	}
	
}
