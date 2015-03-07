package com.start.viewcontrollor;

import org.leanandroid.R;

import android.app.Activity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class RadioButtonControllor extends BaseControllor{

	public RadioButtonControllor(Activity activity) {
		super(activity);
	}
	@Override
	protected void initMember() {
		tv = (TextView)activity.findViewById(R.id.tvSex);
		//根据ID找到RadioGroup实例
		group = (RadioGroup)activity.findViewById(R.id.radioGroup);
	}
	OnCheckedChangeListener listener;
	TextView tv;
	RadioGroup group;
	@Override
	protected void setListeners() {
        //绑定一个匿名监听器
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                //获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton)activity.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                tv.setText("您的性别是：" + rb.getText());
            }
        });
	}

}
