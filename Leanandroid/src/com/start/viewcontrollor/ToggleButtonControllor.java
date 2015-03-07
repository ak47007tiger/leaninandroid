package com.start.viewcontrollor;

import org.leanandroid.R;

import android.app.Activity;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ToggleButtonControllor extends BaseControllor{

	public ToggleButtonControllor(Activity activity) {
		super(activity);
	}
	@Override
	protected void initMember() {
		toggleButton = (ToggleButton)activity.findViewById(R.id.ToggleButton01);
	}
	OnCheckedChangeListener listener;
	ToggleButton toggleButton;
	@Override
	protected void setListeners() {
		listener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				switch (arg0.getId()) {
				case R.id.ToggleButton01:
					String curText = toggleButton.getText().toString();
					Toast.makeText(activity, curText, Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		};
		toggleButton.setOnCheckedChangeListener(listener);
	}

}
