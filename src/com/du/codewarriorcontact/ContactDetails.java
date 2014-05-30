package com.du.codewarriorcontact;

import java.util.Calendar;

import com.du.codewarriorcontact.database.CWDAO;
import com.du.codewarriorcontact.model.Contact;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_details);
		
		String id = getIntent().getStringExtra(Utils.ContactObject);
		
		Contacts ct = CWDAO.getCWdao().getContactDetailsOfId(id);
	}
}
