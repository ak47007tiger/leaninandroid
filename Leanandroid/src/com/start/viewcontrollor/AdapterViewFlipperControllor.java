package com.start.viewcontrollor;

import org.leanandroid.R;

import com.start.util.ListenerUtil;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdapterViewFlipperControllor extends BaseControllor{

	AdapterViewFlipper adapterViewFlipper;
	public AdapterViewFlipperControllor(Activity activity) {
		super(activity);
	}
	
	@Override
	protected void initMember() {
		adapterViewFlipper = 
				(AdapterViewFlipper)activity.findViewById(R.id.generalcontrol_flipper);
	}

	OnClickListener listener_btnclick; 
	@Override
	protected void setListeners() {
		listener_btnclick = new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					switch (arg0.getId()) {
					case R.id.generalcontrol_auto:	
						adapterViewFlipper.startFlipping();
						break;
					case R.id.generalcontrol_prev:	
						adapterViewFlipper.showPrevious();
						adapterViewFlipper.stopFlipping();
						break;
					case R.id.generalcontrol_nextv:	
						adapterViewFlipper.showNext();
						adapterViewFlipper.stopFlipping();
						break;

					default:
						break;
					}
				}
			};
		ListenerUtil.setSameOnClickListener(
				new int[]{R.id.generalcontrol_auto,R.id.generalcontrol_nextv,
						R.id.generalcontrol_prev}, activity, listener_btnclick);
	}
	@Override
	protected void setAdapters() {
		if(null == adapterViewFlipper){
			adapterViewFlipper = 
					(AdapterViewFlipper)activity.findViewById(R.id.generalcontrol_flipper);
		}
		baseAdapter = new BaseAdapter() {
			private int[] m_image_ids = new int[] { R.drawable.upgray,
		            R.drawable.oracle10ginfo, R.drawable.xiwer,R.drawable.windandsord };
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ImageView imageView = new ImageView(activity);
	            imageView.setImageResource(m_image_ids[position]);
	            // 设置image的缩放类型
	            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
	            imageView.setLayoutParams(new LayoutParams(
	                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	            return imageView;
	        }

	        @Override
	        public long getItemId(int position) {
	            return position;
	        }

	        @Override
	        public Object getItem(int position) {
	            return position;
	        }

	        @Override
	        public int getCount() {
	            return m_image_ids.length;
	        }
	    };
		adapterViewFlipper.setAdapter(baseAdapter);
	}
	BaseAdapter baseAdapter;
}
