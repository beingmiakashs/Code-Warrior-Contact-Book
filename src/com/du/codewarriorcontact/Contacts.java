package com.du.codewarriorcontact;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Contacts  extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contacts, container, false);
		setText(view, "BazarDor");
	    return view;
	}

	public void setText(View view, String item) {
		TextView tv = (TextView) view.findViewById(R.id.tempText);
		tv.setText(item);
	}
}
