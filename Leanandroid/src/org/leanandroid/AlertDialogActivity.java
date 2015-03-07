package org.leanandroid;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import loc.fragment.DlgFragmnet;
import loc.objswitch.Handle;
import loc.objswitch.ObjSwitch;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.start.util.AdapterUtil;
import com.start.util.CustomDialog;
import com.start.util.PasswordDialog;

public class AlertDialogActivity extends Activity {
	private static String[] keys = { "title", "remark" };
	private static String[] titles = { "content对话框", "btn对话框", "listView对话框",
			"radio对话框", "check对话框", "custom对话框", "datePicker框", "圆形progress框",
			"长条形progress框", "模态对话框", "外国的模式dlg", "activity模拟", "自定义对话框activity", 
			"dlg fragment模态"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert_dialog);
		List<Map<String, Object>> contents = new LinkedList<Map<String, Object>>();
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[0],
				"0" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[1],
				"1" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[2],
				"2" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[3],
				"3" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[4],
				"4" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[5],
				"5" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[6],
				"6" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[7],
				"7" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[8],
				"8" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] { titles[9],
				"9" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] {
				titles[10], "10" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] {
				titles[11], "11" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] {
				titles[12], "12" }));
		contents.add(AdapterUtil.createMenuItem(keys, new String[] {
				titles[13], "13" }));
		SimpleAdapter adapter = new SimpleAdapter(this, contents,
				R.layout.gridviewitem, new String[] { "title", "remark" },
				new int[] { R.id.gridviewitem_title, R.id.gridviewitem_remark });
		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(listener);
		initListeners();
		num = (TextView) findViewById(R.id.num);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		}, 0, 1000);
	}

	Timer timer = new Timer(true);

	@Override
	protected void onDestroy() {
		timer.cancel();
		super.onDestroy();
	}

	protected void onStop() {
		super.onStop();
	};

	TextView num;
	int num_int = 0;
	Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				num.setText(num_int++ + "");
				break;
			}
		};
	};
	private ObjSwitch objSwitch = new ObjSwitch();

	private void initListeners() {
		objSwitch.addHandle(titles[13], new Handle(){
			@Override
			public Object handle(Object input) {
				DlgFragmnet dlgFragmnet = new DlgFragmnet();
				dlgFragmnet.show(getFragmentManager(), "dlgFragmnet");
				return null;
			}
		});
		objSwitch.addHandle(titles[12], new Handle() {
			@Override
			public Object handle(Object input) {
				Intent intent = new Intent(AlertDialogActivity.this,
						ModalDialog.class);
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Intent intent = new Intent(ModalDialog.action);
						sendBroadcast(intent);
					}
				});
				AlertDialogActivity.this.startActivity(intent);
				thread.start();
				return null;
			}
		});
		objSwitch.addHandle(titles[11], new Handle() {
			@Override
			public Object handle(Object input) {
				Intent intent = new Intent(AlertDialogActivity.this,
						ConfirmActivity.class);
				AlertDialogActivity.this.startActivity(intent);
				return null;
			}
		});
		objSwitch.addHandle(titles[10], new Handle() {
			@Override
			public Object handle(Object input) {
				PasswordDialog dialog = new PasswordDialog(
						AlertDialogActivity.this, "hello", true);
				dialog.setTitle("模式对话框");
				if (dialog.showDialog() == PasswordDialog.OK) {
					Toast.makeText(AlertDialogActivity.this, "单击了ok", 2000)
							.show();
				}
				return null;
			}
		});
		objSwitch.addHandle(titles[9], new Handle() {

			@Override
			public Object handle(Object input) {
				Dialog dialog = new CustomDialog(AlertDialogActivity.this);
				dialog.setTitle("模式对话框");
				dialog.setContentView(R.layout.customdialoglayout);
				dialog.show();
				return null;
			}
		});
		objSwitch.addHandle(titles[8], new Handle() {
			ProgressDialog progressDialog;
			int count = 0;

			@Override
			public Object handle(Object input) {
				// 创建ProgressDialog对象
				progressDialog = new ProgressDialog(AlertDialogActivity.this);
				// 设置进度条风格，风格为长形
				progressDialog
						.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				// 设置ProgressDialog 标题
				progressDialog.setTitle("提示");
				// 设置ProgressDialog 提示信息
				progressDialog.setMessage("这是一个长形对话框进度条");
				// 设置ProgressDialog 标题图标
				progressDialog.setIcon(R.drawable.xiwer);
				// 设置ProgressDialog 进度条进度
				progressDialog.setProgress(100);
				// 设置ProgressDialog 的进度条是否不明确
				progressDialog.setIndeterminate(false);
				// 设置ProgressDialog 是否可以按退回按键取消
				progressDialog.setCancelable(true);
				// 让ProgressDialog显示
				progressDialog.show();
				new Thread() {
					public void run() {
						try {
							while (count <= 100) {
								// 由线程来控制进度。
								progressDialog.setProgress(count);
								count += 5;
								Thread.sleep(100);
							}
							progressDialog.cancel();
						} catch (InterruptedException e) {
							progressDialog.cancel();
						}
					}
				}.start();
				return null;
			}
		});
		objSwitch.addHandle(titles[7], new Handle() {
			@Override
			public Object handle(Object input) {
				final ProgressDialog proDialog = android.app.ProgressDialog
						.show(AlertDialogActivity.this, "测试", "2秒后自动消失！");
				Thread thread = new Thread() {
					public void run() {
						try {
							sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						proDialog.dismiss();// 万万不可少这句，否则会程序会卡死。
					}
				};
				thread.start();
				return null;
			}
		});
		objSwitch.addHandle(titles[6], new Handle() {
			Calendar calendar = Calendar.getInstance();
			OnDateSetListener dateSetListener = new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker arg0, int arg1, int arg2,
						int arg3) {
					Log.i("data set", arg1 + "-" + arg2 + "-" + arg3);
				}
			};

			@Override
			public Object handle(Object input) {
				new DatePickerDialog(AlertDialogActivity.this, dateSetListener,
						calendar.get(Calendar.YEAR), calendar
								.get(Calendar.MONTH), calendar
								.get(Calendar.DAY_OF_MONTH)).show();
				return null;
			}
		});
		objSwitch.addHandle(titles[5], new Handle() {

			@Override
			public Object handle(Object input) {
				LayoutInflater layoutInflater = LayoutInflater
						.from(AlertDialogActivity.this);
				View myLoginView = layoutInflater.inflate(
						R.layout.customdialoglayout, null);

				Dialog alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this)
						.setTitle("用户登录")
						.setIcon(R.drawable.ic_launcher)
						.setView(myLoginView)
						.setPositiveButton("登录",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).create();
				alertDialog.show();
				return null;
			}
		});
		objSwitch.addHandle(titles[0], new Handle() {
			@Override
			public Object handle(Object input) {
				Dialog alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this).setTitle("对话框的标题")
						.setMessage("对话框的内容").setIcon(R.drawable.ic_launcher)
						.create();
				alertDialog.show();
				return null;
			}
		});
		objSwitch.addHandle(titles[1], new Handle() {
			@Override
			public Object handle(Object input) {
				Dialog alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this)
						.setTitle("确定删除？")
						.setMessage("您确定删除该条信息吗？")
						.setIcon(R.drawable.ic_launcher)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								})
						.setNeutralButton("查看详情",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).create();
				alertDialog.show();
				return null;
			}
		});
		objSwitch.addHandle(titles[2], new Handle() {

			@Override
			public Object handle(Object input) {
				Dialog alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this)
						.setTitle("你喜欢吃哪种水果？")
						.setIcon(R.drawable.ic_launcher)
						.setItems(arrayFruit,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Toast.makeText(
												AlertDialogActivity.this,
												arrayFruit[which],
												Toast.LENGTH_SHORT).show();
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).create();
				alertDialog.show();
				return null;
			}
		});
		objSwitch.addHandle(titles[3], new Handle() {
			int selectedFruitIndex;

			@Override
			public Object handle(Object input) {
				Dialog alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this)
						.setTitle("你喜欢吃哪种水果？")
						.setIcon(R.drawable.ic_launcher)
						.setSingleChoiceItems(arrayFruit, 0,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										selectedFruitIndex = which;
									}
								})
						.setPositiveButton("确认",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Toast.makeText(
												AlertDialogActivity.this,
												arrayFruit[selectedFruitIndex],
												Toast.LENGTH_SHORT).show();
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).create();
				alertDialog.show();
				return null;
			}
		});
		objSwitch.addHandle(titles[4], new Handle() {

			@Override
			public Object handle(Object input) {
				final String[] arrayFruit = new String[] { "苹果", "橘子", "草莓",
						"香蕉" };
				final boolean[] arrayFruitSelected = new boolean[] { true,
						true, false, false };
				Dialog alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this)
						.setTitle("你喜欢吃哪种水果？")
						.setIcon(R.drawable.ic_launcher)
						.setMultiChoiceItems(
								arrayFruit,
								arrayFruitSelected,
								new DialogInterface.OnMultiChoiceClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which, boolean isChecked) {
										arrayFruitSelected[which] = isChecked;
									}
								})
						.setPositiveButton("确认",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										StringBuilder stringBuilder = new StringBuilder();
										for (int i = 0; i < arrayFruitSelected.length; i++) {
											if (arrayFruitSelected[i] == true) {
												stringBuilder
														.append(arrayFruit[i]
																+ "、");
											}
										}
										Toast.makeText(
												AlertDialogActivity.this,
												stringBuilder.toString(),
												Toast.LENGTH_SHORT).show();
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).create();
				alertDialog.show();
				return null;
			}
		});
	}

	final String[] arrayFruit = new String[] { "苹果", "橘子", "草莓", "香蕉" };

	private OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String title = ((TextView) arg1
					.findViewById(R.id.gridviewitem_title)).getText()
					.toString();
			objSwitch.DispatchHandle(title, null);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.alert_dialog, menu);
		return true;
	}

}
