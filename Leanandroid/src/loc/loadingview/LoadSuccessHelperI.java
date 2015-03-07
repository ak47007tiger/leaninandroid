package loc.loadingview;

import android.view.View;

public interface LoadSuccessHelperI {
	View getView();
	boolean getSuccess();
	void success();
	void fail();
	void doLoad();
}
