package com.du.codewarriorcontact.settings;

import com.du.codewarriorcontact.R;
import com.du.codewarriorcontact.util.GlobalConstant;
import com.du.codewarriorcontact.util.MessageUtilities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;


public class NearPlaceSettingFragment extends Fragment {
	
	private CheckBox nearPlaceCheck;
	private SharedPreferences preferences;
	private Editor editor;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_near_place, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	
    	preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		editor = preferences.edit();
		
		
		nearPlaceCheck = (CheckBox) getActivity().findViewById(R.id.near_place_check);
		
		
		String status = preferences.getString("sms", "0");
		
		if(status.equals(GlobalConstant.KEY_ON))
			nearPlaceCheck.setChecked(true);
		else
			nearPlaceCheck.setChecked(false);
		
		
		nearPlaceCheck.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (nearPlaceCheck.isChecked())
				{
					MessageUtilities.confirmUser(getActivity(), "Do you really want to start near place status update ?",yesStopClick, noStopClick);
				}
				else
				{
					editor.putString(GlobalConstant.KEY_NEAR_ME_SERVICE, GlobalConstant.KEY_OFF);
					editor.commit();
					Toast.makeText(getActivity(), "Near place status update is disabled now", Toast.LENGTH_SHORT).show();
				}
				DualPaneSettingsRefresher.refreshListAdapter(getActivity());
			}
		});

    }
    
    private DialogInterface.OnClickListener yesStopClick = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			editor.putString(GlobalConstant.KEY_NEAR_ME_SERVICE, GlobalConstant.KEY_ON);
			editor.commit();
			Toast.makeText(getActivity(), "Near place status update is active now", Toast.LENGTH_SHORT).show();
		}
	};

	private DialogInterface.OnClickListener noStopClick = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			nearPlaceCheck.setChecked(false);
		}
	};

}
