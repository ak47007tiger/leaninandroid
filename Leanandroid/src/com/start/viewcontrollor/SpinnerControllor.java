package com.start.viewcontrollor;

import java.util.ArrayList;
import java.util.List;

import org.leanandroid.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.start.vo.Coach;

public class SpinnerControllor extends BaseControllor{

	public SpinnerControllor(Activity activity) {
		super(activity);
	}
	@Override
	protected void initMember() {
		spinner_arr = 
				(Spinner)activity.findViewById(R.id.generalcontrol_spinner_arr);
	}
	@Override
	protected void setListeners() {
		
		spinner_arr.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String str = parent.getItemAtPosition(position).toString();
				Toast.makeText(activity, "ÄãÑ¡ÔñµÄÊÇ:" + str, Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}
	
	Spinner spinner_arr;
	@Override
	protected void setAdapters() {
		String[]strArr = new String[]{"select1","2","3"};
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, 
				android.R.layout.simple_list_item_multiple_choice, strArr);
		
		spinner_arr.setAdapter(arrayAdapter);
		
		
		Spinner spinner_myadpter = 
				(Spinner)activity.findViewById(R.id.generalcontrol_spinner_myadapter);
		List<Coach> list = new ArrayList<Coach>();
		list.add(new Coach().setName("andy").setSex("male"));
		list.add(new Coach().setName("july").setSex("female"));
		myAdapter = new MyAdapter(list, activity);
		spinner_myadpter.setAdapter(myAdapter);
	}
	
	MyAdapter myAdapter;
	class MyAdapter extends BaseAdapter {
		List<Coach> list;
		Context context;
		public MyAdapter(List<Coach> list, Context context) {
			this.list = list;
			this.context = context;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.spinneritem, null);
			TextView name = (TextView)arg1.findViewById(R.id.spinner_name);
			name.setText(list.get(arg0).getName());
			TextView sex = (TextView)arg1.findViewById(R.id.spinner_sex);
			sex.setText(list.get(arg0).getSex());
			return arg1;
		}
		
		@Override
		public long getItemId(int arg0) {
			return arg0;
		}
		
		@Override
		public Object getItem(int arg0) {
			return list.get(arg0);
		}
		
		@Override
		public int getCount() {
			return list.size();
		}
	};
}
