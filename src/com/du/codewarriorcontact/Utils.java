package com.du.codewarriorcontact;

import android.util.Log;

public class Utils {

	public static int SELECTED_ITEM = -1; 
	public static final boolean DEBUG = true;
	public static void print(String ss){
		if(DEBUG){
			Log.i("testing", ss);
		}
	}
}
