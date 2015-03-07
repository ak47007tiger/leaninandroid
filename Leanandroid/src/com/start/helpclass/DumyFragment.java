package com.start.helpclass;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DumyFragment extends Fragment {
	public static String arg_section_num = "section_num";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textView = new TextView(getActivity());
		textView.setGravity(Gravity.START);
		Bundle args = getArguments();
		textView.setText(args.getInt(arg_section_num) + "");
		textView.setTextSize(30);
		return textView;
	}
}