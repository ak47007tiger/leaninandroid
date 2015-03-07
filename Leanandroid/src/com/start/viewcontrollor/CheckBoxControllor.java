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
						//��ѡ�У�����
						EditText info = (EditText)activity.findViewById(R.id.generalcontrol_checkbox_et);
						String text = info.getText().toString();
						info.setText(text + "����");
						Toast.makeText(activity, "������ѡ��", Toast.LENGTH_SHORT).show();
					}else{
						//û��ѡ�У�����
						Toast.makeText(activity, "������ȡ��ѡ��", Toast.LENGTH_SHORT).show();
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
