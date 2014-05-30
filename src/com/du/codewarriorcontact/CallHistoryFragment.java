package com.du.codewarriorcontact;

import java.util.ArrayList;

import com.du.codewarriorcontact.adapter.CustomAdapter;
import com.du.codewarriorcontact.dataprovider.ContactListDataProvider;
import com.du.codewarriorcontact.model.Contact;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CallHistoryFragment extends ListFragment {
	String[] list_items;
	private CustomAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

//		ContactListDataProvider data = new ContactListDataProvider();
//		ArrayList<Contact> list = data.displayContacts();
//
//		adapter = new CustomAdapter(list);
//		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
