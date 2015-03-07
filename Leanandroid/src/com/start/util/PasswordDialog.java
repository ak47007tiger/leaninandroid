package com.start.util;

import org.leanandroid.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class PasswordDialog extends Dialog {
	int dialogResult;
	Handler mHandler;

	public PasswordDialog(Activity context, String mailName, boolean retry) {

		super(context);
		setOwnerActivity(context);
		onCreate();
		TextView promptLbl = (TextView) findViewById(R.id.promptLbl);
		promptLbl.setText("请输入密码/n" + mailName);
	}

	public int getDialogResult() {
		return dialogResult;
	}

	public void setDialogResult(int dialogResult) {
		this.dialogResult = dialogResult;
	}

	/** Called when the activity is first created. */

	public void onCreate() {
		setContentView(R.layout.customdialoglayout);
		findViewById(R.id.cancelBtn).setOnClickListener(
				new android.view.View.OnClickListener() {

					@Override
					public void onClick(View paramView) {
						endDialog(CANCEL);
					}
				});
		findViewById(R.id.okBtn).setOnClickListener(
				new android.view.View.OnClickListener() {

					@Override
					public void onClick(View paramView) {
						endDialog(OK);
					}
				});
	}

	public final static int CANCEL = 1;
	public final static int OK = 0;

	public void endDialog(int result) {
		dismiss();
		setDialogResult(result);
		Message m = mHandler.obtainMessage();
		mHandler.sendMessage(m);
	}

	public int showDialog() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message mesg) {
				// process incoming messages here
				// super.handleMessage(msg);
				throw new RuntimeException();
			}
		};
		super.show();
		try {
			Looper.getMainLooper().loop();
		} catch (RuntimeException e2) {
		}
		return dialogResult;
	}

}
