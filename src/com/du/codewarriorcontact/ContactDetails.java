package com.du.codewarriorcontact;

import java.util.Calendar;

import com.du.codewarriorcontact.database.CWDAO;
import com.du.codewarriorcontact.model.Contact;
import com.sakin.sohojshoncoi.R;
import com.sakin.sohojshoncoi.settings.SettingsArrayAdapter;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetails extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_details);
		
		String id = getIntent().getStringExtra(Utils.ContactObject);

		String[] values = new String[] { "Reminder tone", "Add Transaction on Paid Reminder", "About This App", "About Us", "Credits" };
		 SettingsArrayAdapter adapter = new SettingsArrayAdapter(getActivity(),
		    R.layout.settings_item, values);
		setListAdapter(adapter);
		
		
		try {
			Contact ct = CWDAO.getCWdao().getContactDetailsOfId(Long.parseLong(id));
			
			ImageView iv = (ImageView) findViewById(R.id.photo);
			iv.setImageBitmap(Utils.retrieveContactPhoto(this, Long.parseLong(id)));
			
			TextView nameView = (TextView) findViewById(R.id.name);
			TextView phoneView = (TextView) findViewById(R.id.phone);
			TextView emailView = (TextView) findViewById(R.id.email);
			
			nameView.setText(ct.firstName);
			
			//phoneView.setText();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
