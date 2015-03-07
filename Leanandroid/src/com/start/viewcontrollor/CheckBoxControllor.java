package com.start.viewcontrollor;

import org.leanandroid.R;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class CheckBoxControllor extends BaseControllor{

	public CheckBoxControllor(Activity activity) {
		super(activity);
	}
	@Override
	protected void initMember() {
		beijing = (CheckBox)activity.findViewById(R.id.generalcontrol_cb_beijing);
	}
	CheckBox beijing;
	@Override
	protected void setListeners(){
		listener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				switch (arg0.getId()) {
				case R.id.generalcontrol_cb_beijing:
					if(arg1){
						//被选中，处理
						EditText info = (EditText)activity.findViewById(R.id.generalcontrol_checkbox_et);
						String text = info.getText().toString();
						info.setText(text + "北京");
						Toast.makeText(activity, "北京被选中", Toast.LENGTH_SHORT).show();
					}else{
						//没被选中，处理
						Toast.makeText(activity, "北京被取消选中", Toast.LENGTH_SHORT).show();
					}
					break;

				default:
					break;
				}
			}
		};
		beijing.setOnCheckedChangeListener(listener);
	}
	protected OnCheckedChangeListener listener;
}
