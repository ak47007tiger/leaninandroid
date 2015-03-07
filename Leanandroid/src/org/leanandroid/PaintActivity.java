package org.leanandroid;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.start.util.AdapterUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class PaintActivity extends Activity {

	View preView;
	ListView paintViewsList;
	FrameLayout viewContainer;
	List<Map<String, Object>> contents = new LinkedList<Map<String, Object>>();
	String[] keys = {"classpkg","itemindex"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paint);
		paintViewsList = (ListView) findViewById(R.id.paintviewlist);
		viewContainer = (FrameLayout) findViewById(R.id.viewcontainer);
		paintViewsList.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(null != preView){
					
//					viewContainer.removeAllViews();
					viewContainer.removeView(preView);
				}
				try {
					View v;
					Class<View> viewClass = (Class<View>) Class.forName(contents.get(arg2).get(keys[0]).toString()); 
					Constructor<View> constructor = viewClass.getConstructor(Context.class);
					v = constructor.newInstance(PaintActivity.this);
					preView = v;
					viewContainer.addView(v);
				}catch(Exception e){
					Log.d("paint", e.getMessage());
				}
				/* catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}*/
			}
		});
		String[]classNames = {"PaintPathView","TestPaintBaseView"};
		String pkg = "loc.paintview.";
		int i = 0;
		for(String className: classNames){
			contents.add(AdapterUtil.createMenuItem(keys, 
					new Object[]{pkg + className ,i++}));
		}
		SimpleAdapter adapter = new SimpleAdapter(this, contents,
				R.layout.paintview_listitem, keys, new int[] {R.id.classpkg,R.id.itemindex});
		paintViewsList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.paint, menu);
		return true;
	}

}
