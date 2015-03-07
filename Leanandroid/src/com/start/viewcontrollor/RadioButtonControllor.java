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
		//����ID�ҵ�RadioGroupʵ��
		group = (RadioGroup)activity.findViewById(R.id.radioGroup);
	}
	OnCheckedChangeListener listener;
	TextView tv;
	RadioGroup group;
	@Override
	protected void setListeners() {
        //��һ������������
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                //��ȡ������ѡ�����ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //����ID��ȡRadioButton��ʵ��
                RadioButton rb = (RadioButton)activity.findViewById(radioButtonId);
                //�����ı����ݣ��Է���ѡ����
                tv.setText("�����Ա��ǣ�" + rb.getText());
            }
        });
	}

}
