package com.du.codewarriorcontact;

import java.sql.SQLException;
import java.util.ArrayList;

import com.du.codewarriorcontact.adapter.CustomAdapter;
import com.du.codewarriorcontact.database.CWDAO;
import com.du.codewarriorcontact.dataprovider.ContactListDataProvider;
import com.du.codewarriorcontact.model.Contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactFragment extends ListFragment{
	String[] list_items;
	private CustomAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
//		ContactListDataProvider data = new ContactListDataProvider();
//	      ArrayList<Contact> list = data.displayContacts();
	      ArrayList<Contact> list;
		try {
			list = (ArrayList<Contact>) CWDAO.getCWdao().getContactsList(getActivity());
			adapter = new CustomAdapter(list) ;
		    setListAdapter(adapter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Utils.print("Cant fetch Contact Data");
		}
		
	      return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	  	Utils.print("clicked");

	  	Contact ct = (Contact) adapter.getItem(position);
	  	Intent detailsIntent = new Intent(getActivity(), ContactDetails.class);
	  	detailsIntent.putExtra(Utils.ContactObject, ct.id);
	  	getActivity().startActivity(detailsIntent);
	}
}
