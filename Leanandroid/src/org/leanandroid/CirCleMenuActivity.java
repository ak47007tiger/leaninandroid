package org.leanandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class CirCleMenuActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.circlemenulayout);
		ViewGroup vg = (ViewGroup) findViewById(R.id.circlemenu);
		int count = vg.getChildCount();
		int i;
		View v;
		for(i = 0; i < count; i++){
			v = vg.getChildAt(i);
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i("circle menu", ((TextView)v).getText().toString());
				}
			});
		}
	}
}
