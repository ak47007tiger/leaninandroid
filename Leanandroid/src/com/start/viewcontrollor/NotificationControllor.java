package com.start.viewcontrollor;

import org.leanandroid.R;
import org.leanandroid.IndexMenuActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NotificationControllor extends BaseControllor{

	Button btn_send;
	Button btn_cancle;
	EditText et_msg;
	NotificationManager mNotificationManager;  
    Intent intent;
    PendingIntent pendingIntent;
    Builder notificationBuilder;
	public NotificationControllor(Activity activity) {
		super(activity);
	}
	@Override
	protected void initMember() {
		btn_send = (Button)findViewById(R.id.notification_send);
		btn_cancle = (Button)findViewById(R.id.notification_cancle);
		et_msg = (EditText)findViewById(R.id.notification_msg);
		//��ȡNotificationManager������   
        String ns = Context.NOTIFICATION_SERVICE;  
        mNotificationManager = (NotificationManager)activity.getSystemService(ns);  
        
        // ����notification����Ϣ �� PendingIntent  
        // ����֪ͨ�����ת��SimpleadapterActivity
        intent = new Intent(activity,  
        		IndexMenuActivity.class);  
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        // ��ȡPendingIntent,���ʱ���͸�Intent  
        pendingIntent = PendingIntent.getActivity(activity, 0,  
        		intent, 0);
        notificationBuilder = new Builder(activity).setSmallIcon(R.drawable.xiwer)
                .setTicker("Ӧ��notification").setWhen(System.currentTimeMillis())
                .setContentTitle("msg").setNumber(1)
                .setContentIntent(pendingIntent);
	}
	private static int msgid = 0; 
	@Override
	protected void setListeners() {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch (arg0.getId()) {
				case R.id.notification_send:
					 //��ʼ��Notification  
			        Notification mNotification;
			        notificationBuilder.setContentText(et_msg.getText().toString());
			        mNotification = notificationBuilder.build();
			        mNotification.defaults = Notification.DEFAULT_ALL;  
			        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;  
			        mNotification.flags |= Notification.FLAG_SHOW_LIGHTS;  
		            // ����֪ͨ  
			        try{
			        	mNotificationManager.notify(msgid, mNotification);
			        }catch(Exception e){
			        	e.printStackTrace();
			        }
					break;
				case R.id.notification_cancle:
					mNotificationManager.cancel(msgid);
					break;
				default:
					break;
				}
			}
		};
		btn_cancle.setOnClickListener(listener);
		btn_send.setOnClickListener(listener);
	}

}
