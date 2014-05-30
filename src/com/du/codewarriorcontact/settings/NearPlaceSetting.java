package com.du.codewarriorcontact.settings;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.du.codewarriorcontact.R;
import com.du.codewarriorcontact.util.GlobalConstant;
import com.du.codewarriorcontact.util.MessageUtilities;

public class NearPlaceSetting extends Activity {
	
	private CheckBox nearPlaceCheck;
	private SharedPreferences preferences;
	private Editor editor;

	TextView initialTXT;
	TextView detailsTXT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_near_place);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = preferences.edit();
		
		initialTXT = (TextView)findViewById(R.id.radiusinfo);
		detailsTXT = (TextView)findViewById(R.id.radText);
		nearPlaceCheck = (CheckBox) findViewById(R.id.near_place_check);
		
		String status = preferences.getString("sms", "0");
		
		if(status.equals("on"))
			nearPlaceCheck.setChecked(true);
		else
			nearPlaceCheck.setChecked(false);
		
		
		nearPlaceCheck.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (nearPlaceCheck.isChecked())
				{
					MessageUtilities.confirmUser(NearPlaceSetting.this, "Do you really want to start near place status update ?",yesStopClick, noStopClick);
				}
				else
				{
					editor.putString(GlobalConstant.KEY_NEAR_ME_SERVICE, GlobalConstant.KEY_OFF);
					editor.commit();
					Toast.makeText(getApplicationContext(), "Near place status update is disabled now", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private DialogInterface.OnClickListener yesStopClick = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			editor.putString(GlobalConstant.KEY_NEAR_ME_SERVICE, GlobalConstant.KEY_ON);
			editor.commit();
			Toast.makeText(getApplicationContext(), "Near place status update is active now", Toast.LENGTH_SHORT).show();
		}
	};

	private DialogInterface.OnClickListener noStopClick = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			nearPlaceCheck.setChecked(false);
		}
	};

}