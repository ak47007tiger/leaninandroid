package com.start.viewcontrollor;

import org.leanandroid.R;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.start.util.InputUtil;

public class AutoCompletTVControllor extends BaseControllor{
	public AutoCompletTVControllor(Activity activity) {
		super(activity);
	}
	@Override
	protected void initMember() {
		autotv = (AutoCompleteTextView)activity.findViewById(
				R.id.generalcontrol_autoclttv);
		mulautotv = (MultiAutoCompleteTextView)activity.findViewById(R.id.generalcontrol_mulautoclttv);
	}
	private static String[] objects = {"text1","text2","text3"};
	AutoCompleteTextView autotv;
	@Override
	protected void setListeners() {
		autotv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				InputUtil.closeInputMethod(activity, arg0.getChildAt(arg2));
			}
		});
		mulautotv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
	MultiAutoCompleteTextView mulautotv;
	@Override
	protected void setAdapters() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_dropdown_item_1line, objects);
		autotv.setAdapter(adapter);
		mulautotv.setAdapter(adapter);
	}
}
