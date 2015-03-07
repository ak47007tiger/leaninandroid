package loc.loadingview;

import org.leanandroid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class SimpleLoadSuccessHelperImpl implements LoadSuccessHelperI{
	private View successView;
	private Context context;
	private boolean success = false;
	public SimpleLoadSuccessHelperImpl(View v) {
		successView = v;
	}
	public SimpleLoadSuccessHelperImpl(Context context) {
		this.context = context;  
	}
	public View getView(){
		return successView;
	}
	public boolean getSuccess(){
		return success;
	}
	@Override
	public void doLoad() {
		try{
			successView = LayoutInflater.from(context).inflate(R.layout.tablelayout, null);
			success = true;
		}catch(Exception e){
			success = false;
			successView = LayoutInflater.from(context).inflate(R.layout.activity_file_ops, null);
			e.getMessage();
		}
	}
	@Override
	public void success() {
		this.success = true;
	}
	@Override
	public void fail() {
		this.success = false;
	}
}
