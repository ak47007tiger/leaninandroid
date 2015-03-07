package loc.loadingview;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author huangpl
 *
 */
public class LoadingHelper{
	private ViewGroup parentContainer;
	private Handler handler;
	private int msg_success;
	private int msg_fail;
	private boolean loadComplet = false;
	private LoadSuccessHelperI loadSuccessHelper;
	public LoadingHelper(Handler handler, ViewGroup parentContainer,LoadSuccessHelperI successHelper, int msg_success, int msg_fail) {
		this.parentContainer = parentContainer;
		this.handler = handler;
		this.msg_fail = msg_fail;
		this.msg_success = msg_success;
		loadSuccessHelper = successHelper;
	}
	public void doLoad(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				loadSuccessHelper.doLoad();
				try {
					Thread.sleep(1000);//模拟耗时操作
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loadComplet = true;
				if(loadSuccessHelper.getSuccess()){
					onLoadSucess();
				}else{
					onLoadFail();
				}
			}
		}).start();
	}
	public View getView(){
		return loadSuccessHelper.getView();
	}
	public void replace() throws Exception{
		if(loadComplet){
			parentContainer.removeAllViews();
			if(loadSuccessHelper.getSuccess()){
				parentContainer.addView(loadSuccessHelper.getView());
			}else{
				//添加提示失败信息的处理
			}
		}else{
			throw new Exception("success view not complet");
		}
	}
	private void onLoadSucess() {
		handler.sendEmptyMessage(msg_success);
	}
	private void onLoadFail() {
		handler.sendEmptyMessage(msg_fail);
	}
}
